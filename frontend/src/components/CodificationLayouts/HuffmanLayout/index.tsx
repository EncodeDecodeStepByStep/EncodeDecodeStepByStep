import React, { ReactElement, useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { HuffmanCodewordRow, Count, TreeContainer, Container } from "./styles";
import { useCodewords, useIndex, useTheme } from "../../../context";
import { Codeword } from "../../../models/codeword";
import { huffmanHashes } from "../../../hooks";
import { Typografy } from "../../Typografy";
import Tree from "react-tree-graph";
import "react-tree-graph/dist/style.css";

export const HuffmanLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();

  const [huffmanCount, setHuffmanCount] = useState([]);
  const [huffmanTree, setHuffmanTree] = useState([]);
  const [orderedHuffmanCount, setOrderedHuffmanCount] = useState([]);
  const [theme] = useTheme();

  function orderHuffman(huffmanCount, tree) {
    const orderHuffman = Array<any>();
    tree.forEach((node) => {
      const characterAscii = node[0].charCodeAt(0);
      huffmanCount.forEach((huffmanCountNode) => {
        if (parseInt(huffmanCountNode[0]) === parseInt(characterAscii)){
          orderHuffman.push(huffmanCountNode);
        }
      });
    });

    return orderHuffman;
  }

  useEffect(() => {
    async function getHaches() {
      const huffman = await huffmanHashes();

      if (huffman.huffmanCount) {
        const tree = Object.entries(huffman.huffmanTree);
        tree.sort((a, b) => {
          const codewordA = a[1] as string;
          const codewordB = b[1] as string;
          return codewordA.length > codewordB.length
            ? 1
            : codewordA < codewordB
            ? -1
            : 1;
        });
        setHuffmanTree(tree);

        const huffmanCountArray = Object.entries(huffman.huffmanCount);

        setHuffmanCount(huffmanCountArray);

        const orderedHuffmanCount = orderHuffman(huffmanCountArray, tree);
        setOrderedHuffmanCount(orderedHuffmanCount);
      }
    }
    getHaches();
  }, []);

  function renderCodeword(codeword: Codeword, index: number) {
    return (
      <HuffmanCodewordRow isDark={theme} key={index}>
        <span className="codeword">{codeword.codeword}</span>
        <Icon.TransformTo size={15} color={PRIMARY} />
        <span className="codevalue">{codeword.value}</span>
      </HuffmanCodewordRow>
    );
  }

  function renderCodewords() {
    const layoutArray = Array<ReactElement>();
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      if (codeword) {
        let codewordLayout = renderCodeword(codeword, i);
        layoutArray.push(codewordLayout);
      }
    }
    return layoutArray;
  }

  function renderList(list, withCharCode) {
    function isSelected(value) {
      const lastCodeword = codewords[index - 1];

      if (
        lastCodeword &&
        value &&
        !withCharCode &&
        lastCodeword.codeword === value
      ) {
        return true;
      } else {
        return false;
      }
    }

    return list.length ? (
      <div className="counter-list">
        {list.map((line, index) => {
          return (
            <div
              className={isSelected(line[1]) ? "line selected-row" : "line"}
              key={index}
            >
              <span>
                {withCharCode ? String.fromCharCode(line[0]) : line[0]}
              </span>
              <Icon.TransformTo size={15} color={PRIMARY} />
              <span>{line[1]}</span>
            </div>
          );
        })}
      </div>
    ) : (
      <></>
    );
  }

  function verifyPath(value) {
    const lastCodeword = codewords[index - 1];

    if (lastCodeword) {
      return lastCodeword.codeword.startsWith(value)
        ? "write-path"
        : "normal-path";
    } else {
      return "";
    }
  }

  function extractData(list) {
    const data = {
      name: "",
      children: [],
    };

    for (let i = 0; i < list.length; i++) {
      const treeCell = list[i];
      const symbol = treeCell[0];
      const codewordSimbol = treeCell[1];

      let atualChildren = data.children;
      for (let j = 1; j <= codewordSimbol.length; j++) {
        const codewordLetter = codewordSimbol.substring(0, j);
        if (atualChildren.length === 0) {
          atualChildren.push({
            pathProps: { className: verifyPath(codewordLetter) },
            name:
              codewordLetter === codewordSimbol
                ? codewordLetter + "[" + symbol + "]"
                : codewordLetter,
            textProps: { x: -25, y: 15 },
            children: [],
          });
          atualChildren = atualChildren[0].children;
        } else {
          const existThisCodeword = atualChildren.filter(
            (children) => children.name === codewordLetter
          );

          if (existThisCodeword.length) {
            atualChildren = existThisCodeword[0].children;
          } else {
            atualChildren.push({
              pathProps: { className: verifyPath(codewordLetter) },
              name:
                codewordLetter === codewordSimbol
                  ? codewordLetter + "[" + symbol + "]"
                  : codewordLetter,
              textProps: { x: -25, y: 15 },
              children: [],
            });
            atualChildren = atualChildren[atualChildren.length - 1].children;
          }
        }
      }
    }
    return data;
  }

  function getHeightOfTree(){
    if(huffmanTree.length>17){
      return 900;
    }else if(huffmanTree.length>13){
      return 600;
    }else if(huffmanTree.length>10){
      return 450;
    }else if(huffmanTree.length>5){
      return 400;
    }else{
      return 300;
    }
  }

  return (
    <Container isDark={theme}>
      <div className="first-column">
        <div className="counters">
          <Count isDark={theme}>
            <Typografy.EMPHASYS text="Counting" />
            {renderList(huffmanCount, true)}
          </Count>

          <Count isDark={theme}>
            <Typografy.EMPHASYS text="Ordered Counting" />
            {renderList(orderedHuffmanCount, true)}
          </Count>

          <Count isDark={theme}>
            <Typografy.EMPHASYS text="Codewords" />

            {renderList(huffmanTree, false)}
          </Count>
        </div>

        <TreeContainer isDark={theme}>
          {
            huffmanTree.length&&
            <Tree
              data={extractData(huffmanTree)}
              nodeRadius={15}
              margins={{ top: 25, bottom: 25, left: 40, right: 40 }}
              height={getHeightOfTree()}
              width={600}
            />
          }
         
        </TreeContainer>
      </div>

      <div className="second-column">
        <Typografy.EMPHASYS text="Encoding" />
        {renderCodewords()}
      </div>
    </Container>
  );
};
