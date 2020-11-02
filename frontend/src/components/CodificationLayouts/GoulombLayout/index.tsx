import React, { useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { GoulombCodewordRow, Header, Explanation } from "./styles";
import { useCodewords, useIndex, useGoulombDivisor } from "../../../context";
import { Codeword } from "../../../models/codeword";

export const GoulombLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [goulombDivisor] = useGoulombDivisor();

  const [stopBitPosition, setStopBitPosition] = useState(-1);

  useEffect(() => {
    if (stopBitPosition == -1 && codewords.length) {
      const firstCodeword = codewords[0].codeword.toString();
      for (let i = 0; i < firstCodeword.length; i++) {
        if (firstCodeword.charAt(i) === "1") {
          setStopBitPosition(i);
          break;
        }
      }
    }
  }, [codewords]);

  function renderExplanation(codeword: Codeword) {
    const quantityOfZeros = codeword.codeword.substring(0, stopBitPosition)
      .length;

    const rest = codeword.codeword.substring(stopBitPosition + 1);
    const binaryRest = parseInt(rest, 2);

    const integerPart = goulombDivisor * quantityOfZeros;
    const finalResult = integerPart + binaryRest;

    return (
      <Explanation>
        <div className="first-line">
          <span className="prefix">
            <strong>{quantityOfZeros}</strong>
            &nbsp;[0`s]
          </span>
          &amp;
          <span className="sufix">
            {rest}
            &nbsp; [<strong>{binaryRest}</strong> em binário]
          </span>
          =
          <span className="result">
            {goulombDivisor}
            <sup>[K]</sup> * &nbsp;
            <strong>{quantityOfZeros}</strong>
            &nbsp; + &nbsp;
            <strong>{binaryRest}</strong>
            &nbsp; = &nbsp;
            <strong>{finalResult}</strong>
          </span>
        </div>

        <div className="second-line">
          <span className="ascii">ASCII {finalResult}</span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span className="codevalue">{codeword.value}</span>
        </div>
      </Explanation>
    );
  }

  function renderCodewordSplitted(codeword: string) {
    return (
      <div className="codeword">
        <span className="unaryPart">
          {codeword.substring(0, stopBitPosition)}
        </span>
        <span className="stopbit">{codeword.charAt(stopBitPosition)}</span>
        <span className="rest">{codeword.substring(stopBitPosition + 1)}</span>
      </div>
    );
  }

  function renderCodeword(codeword: Codeword, index: number) {
    return (
      <GoulombCodewordRow key={index}>
        {renderCodewordSplitted(codeword.codeword)}
        {renderExplanation(codeword)}
      </GoulombCodewordRow>
    );
  }

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      if (codeword) {
        layoutArray.push(renderCodeword(codeword, i));
      }
    }
    return layoutArray;
  }

  return (
    <>
      <Header>
        <span className="unaryPart">Prefixo</span>
        <span className="stopbit">Stop Bit</span>
        <span className="rest">Sufixo</span>
      </Header>
      {renderCodewords()}
    </>
  );
};
