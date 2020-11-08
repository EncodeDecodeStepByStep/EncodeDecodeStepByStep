import React from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { UnaryCodewordRow } from "./styles";
import { useCodewords, useCodingDecoding, useIndex, useTheme } from "../../../context";
import { Codeword } from "../../../models/codeword";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";

export const UnaryLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [theme] = useTheme();
  const [codingDecoding] = useCodingDecoding();

  function renderCodeword(codeword: Codeword, index:number) {
    if (codeword) {
      return (
        <UnaryCodewordRow isDark={theme} key={index}>
          {codingDecoding === EncodingDecoding.DECODING ? (
            <>
              <span className="codeword">{codeword.codeword}</span>
              <div>
                <span className="ascii">ASCII</span>
                <strong className="code">
                  {codeword.codeword.length} ({codeword.codeword.charAt(0)}`s)
                </strong>
                <div className="icon-container">
                  <Icon.TransformTo size={15} color={PRIMARY} />
                </div>
                <span className="codevalue">{codeword.value}</span>
              </div>
            </>
          ) : (
            <>
              <div>
                <span className="codevalue">{codeword.value}</span>
                <div className="icon-container">
                  <Icon.TransformTo size={15} color={PRIMARY} />
                </div>
                
                <strong className="code">
                  {codeword.codeword.length} ({codeword.codeword.charAt(0)}`s)
                </strong>
                <span className="ascii">(ASCII)</span>
              </div>
              <span className="codeword">{codeword.codeword}</span>
            </>
          )}
        </UnaryCodewordRow>
      );
    }
  }

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      layoutArray.push(renderCodeword(codeword, i));
    }
    return layoutArray;
  }

  return <>{renderCodewords()}</>;
};
