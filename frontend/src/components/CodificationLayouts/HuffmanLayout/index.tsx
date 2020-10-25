import React, {useEffect} from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { HuffmanCodewordRow } from "./styles";
import { useCodewords, useIndex } from "../../../context";
import { Codeword } from "../../../models/codeword";
import {huffmanHashes} from '../../../hooks'


export const HuffmanLayout = () => {
  const [index, ] = useIndex();
  const [codewords,] = useCodewords();

  useEffect(()=>{
    async function getHaches(){
      const a = await huffmanHashes();
      console.log(a);
    }

    getHaches();
    
  },[])

  function renderCodeword(codeword: Codeword) {
    if (codeword) {
      return (
        <HuffmanCodewordRow>
          <span className="codeword">{codeword.codeword}</span>
          <div>
            <span className="ascii">ASCII</span>
            <strong className="code">{codeword.codeword.length} ({codeword.codeword.charAt(0)}`s)</strong>
            <Icon.TransformTo size={15} color={PRIMARY} />
            <span className="codevalue">
              {codeword.value}
            </span>
          </div>
        </HuffmanCodewordRow>
      );
    }
  };

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      layoutArray.push(renderCodeword(codeword));
    }
    return layoutArray;
  };

  return <>
    {
      renderCodewords()
    }

  </>;
};
