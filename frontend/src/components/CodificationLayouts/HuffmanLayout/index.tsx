import React, { ReactElement, useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { HuffmanCodewordRow, Count, TreeContainer, Container } from "./styles";
import { useCodewords, useIndex } from "../../../context";
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

  function orderHuffman(huffmanCount) {
    const copyArray = huffmanCount.slice(0);
    copyArray.sort((a, b) => {
      const firstCounter = a[1];
      const secondCounter = b[1];
      return firstCounter >= secondCounter ? -1 : 1;
    });
    return copyArray;
  }

  useEffect(() => {
    async function getHaches() {
      const huffman = await huffmanHashes();
      if (huffman.huffmanCount) {
        const huffmanCountArray = Object.entries(huffman.huffmanCount);

        setHuffmanCount(huffmanCountArray);
        setOrderedHuffmanCount(orderHuffman(huffmanCountArray));

        const tree = Object.entries(huffman.huffmanTree);
        tree.sort((a, b) => {
          const codewordA = a[1];
          const codewordB = b[1];
          return codewordA.length > codewordB.length ? -1 : 1;
        });
        setHuffmanTree(tree);
      }
    }
    getHaches();
  }, []);

  function renderCodeword(codeword: Codeword, index: number) {
    return (
      <HuffmanCodewordRow key={index}>
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

    function isSelected(value){
      const lastCodeword = codewords[index-1];

      if(lastCodeword && value  && !withCharCode && lastCodeword.codeword == value){     
        return true;
      }else{
        return false;
      }
    }

   return list.length?    
      <div>
        {list.map((line, index) => {
          return (
            <div className={isSelected(line[1])? 'line selected-row' :'line'}key={index}>
              <span>{
                withCharCode ? String.fromCharCode(line[0]) : line[0]}
              </span>
              <Icon.TransformTo size={15} color={PRIMARY} />
              <span>{line[1]}</span>
            </div>
          );
        })}
      </div>
    : <></>
  }

  function insertDataLine(list, data) {
    if (list.length <=1) {
      return {
        name: "Raiz",
        textProps: { x: -25, y: 15 },
        children: [
          data,
          {
            name: `${list[0][1]} (${list[0][0]})`,
            pathProps: {className: verifyPath(list[0][1], true)},
            textProps: { x: -25, y: 15 },
          },         
        ],
      };
    } else {
      return insertDataLine(list.slice(1),{
        name: "",
        textProps: { x: -25, y: 15 },
        pathProps: {className: verifyPath([list[0][1]], false)},
        children: [
          data,
          {
            name: `${list[0][1]} (${list[0][0]})`,
            pathProps: {className: verifyPath(list[0][1], true)},
            textProps: { x: -25, y: 15 },
          },
        ],
      });
    }
  }

  function verifyPath(value, isLeaf){
   
    const lastCodeword = codewords[index-1];

    if(lastCodeword && value){      
      if(isLeaf){
        return lastCodeword.codeword == value ?'write-path':'normal-path'
      }else{
        if(value.length==2){
          const firstCondition = lastCodeword.codeword.startsWith(value[0]);
          const secondCondition = lastCodeword.codeword.startsWith(value[1])
          return firstCondition || secondCondition?'write-path':'normal-path'
        }
        return lastCodeword.codeword.startsWith(value[0])?'write-path':'normal-path'        
      }      
    }else{
      return ''
    }
    
  }

  function extractData(list) {        
    if(list.length>2){
      const data = {
        name: "",
        textProps: { x: -25, y: 15 },
        pathProps: {className: verifyPath([list[0][1],list[1][1]], false)},
        children: [
          {
            name: `${list[0][1]} (${list[0][0]})`,
            pathProps: {className: verifyPath(list[0][1], true)},
            textProps: { x: -25, y: 15 },
          },
          {
            name: `${list[1][1]} (${list[1][0]})`,
            pathProps: {className: verifyPath(list[1][1], true)},
            textProps: { x: -25, y: 15 },
          },
        ],
      };
    
      return  insertDataLine(list.slice(2), data);
    }else{
      return {}
    }
  
  }

  return (
    <Container>
      <div className="first-column">
        <div className="counters">
          <Count>
            <Typografy.EMPHASYS text="Contagem" />
            {renderList(huffmanCount, true)}
          </Count>

          <Count>
            <Typografy.EMPHASYS text="Contagem Ordenada" />
            {renderList(orderedHuffmanCount, true)}
          </Count>

          <Count>
            <Typografy.EMPHASYS text="Codewords" />
            
            {renderList(huffmanTree.slice(0).reverse(), false)}
          </Count>
        </div>

        <TreeContainer>
          <Typografy.EMPHASYS text="Arvore" />
          <Tree
            data={extractData(huffmanTree)}
            nodeRadius={15}
            margins={{ top: 25, bottom: 25, left: 40, right: 40 }}
            height={300}
            width={600}
          />
        </TreeContainer>
      </div>
      <div className="second-column">
        <Typografy.EMPHASYS text="Codificacão" />
        {renderCodewords()}
      </div>
    </Container>
  );
};
