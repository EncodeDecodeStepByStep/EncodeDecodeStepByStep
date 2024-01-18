import React, { useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { ElliasCodewordRow, Explanation } from "./styles";
import {
  useCodewords,
  useCodingDecoding,
  useIndex,
  useTheme,
} from "../../../context";
import { Codeword } from "../../../models/codeword";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";

export const EliasGammaLayout = () => {
  const [codewords] = useCodewords();
  const [index] = useIndex();

  const [stopBitPosition, setStopBitPosition] = useState(-1);
  const [codingDecoding] = useCodingDecoding();
  const [theme] = useTheme();

  useEffect(() => {
    if (stopBitPosition === -1 && codewords.length) {
      const firstCodeword = codewords[0].codeword.toString();
      for (let i = 0; i < firstCodeword.length; i++) {
        if (firstCodeword.charAt(i) === "1") {
          setStopBitPosition(i);
          break;
        }
      }
    }
  }, [codewords, stopBitPosition]);

  function renderExplanation(codeword: Codeword) {
    const quantityOfZeros = codeword.codeword.substring(0, stopBitPosition)
      .length;

    const rest = codeword.codeword.substring(stopBitPosition + 1);
    const binaryRest = parseInt(rest, 2);

    const integerPart = Math.pow(2, quantityOfZeros);
    const finalResult = integerPart + binaryRest;

    return (
      <Explanation isDark={theme}>
        {codingDecoding === EncodingDecoding.ENCODING &&
          renderAsciiEquality(finalResult, codeword.value)}

        <div className="first-line">
          {codingDecoding === EncodingDecoding.DECODING ? (
            <>
              {renderCountResume(quantityOfZeros, rest, binaryRest)}
              <div className="icon-container">
                <Icon.TransformTo size={15} color={PRIMARY} />
              </div>
              {renderResult(quantityOfZeros, binaryRest, finalResult)}
            </>
          ) : (
            <>
              {renderResult(quantityOfZeros, binaryRest, finalResult)}
              <div className="icon-container">
                <Icon.TransformTo size={15} color={PRIMARY} />
              </div>
              {renderCountResume(quantityOfZeros, rest, binaryRest)}
            </>
          )}
        </div>

        {codingDecoding === EncodingDecoding.DECODING &&
          renderAsciiEquality(finalResult, codeword.value)}
      </Explanation>
    );
  }

  function renderAsciiEquality(finalResult, value) {
    return (
      <div className="second-line">
        {codingDecoding === EncodingDecoding.DECODING ? (
          <>
            <span className="ascii">ASCII {finalResult - 1}</span>
            <div className="icon-container">
              <Icon.TransformTo size={15} color={PRIMARY} />
            </div>
            <span className="codevalue">{value}</span>
          </>
        ) : (
          <>
            <span className="codevalue">{value}</span>
            <div className="icon-container">
              <Icon.TransformTo size={15} color={PRIMARY} />
            </div>
            <span className="ascii">{finalResult - 1}[Ascii]</span>
          </>
        )}
      </div>
    );
  }

  function renderCountResume(quantityOfZeros, rest, binaryRest) {
    return (
      <>
        <span className="prefix">
          <strong>{quantityOfZeros}</strong>
          &nbsp;[0`s]
        </span>
        &amp;
        <span className="sufix">
          {rest}
          &nbsp; [<strong>{binaryRest}</strong> in binary]
        </span>
      </>
    );
  }

  function renderResult(quantityOfZeros, binaryRest, finalResult) {
    return (
      <span className="result">
        {codingDecoding === EncodingDecoding.ENCODING ? (
          <>
            <strong>{finalResult - 1}</strong>
            &nbsp; + &nbsp; 1 &nbsp; = &nbsp;
            {finalResult}
            &nbsp; = &nbsp;2
            <sup>
              <strong>{quantityOfZeros}</strong>
            </sup>
            + &nbsp;
            <strong>{binaryRest}</strong>   
          </>
        ) : (
          <>
            2
            <sup>
              <strong>{quantityOfZeros}</strong>
            </sup>
            + &nbsp;
            <strong>{binaryRest}</strong>
            &nbsp; = &nbsp;
            {finalResult}
            &nbsp; - &nbsp; 1 &nbsp; = &nbsp;
            <strong>{finalResult - 1}</strong>
          </>
        )}
      </span>
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

  function renderCodeword(codeword: Codeword, index:number) {
    return (
      <ElliasCodewordRow isDark={theme} key={index}>
        {codingDecoding === EncodingDecoding.DECODING ? (
          <>
            {renderCodewordSplitted(codeword.codeword)}
            {renderExplanation(codeword)}
          </>
        ) : (
          <>
            {renderExplanation(codeword)}
            {renderCodewordSplitted(codeword.codeword)}
          </>
        )}
      </ElliasCodewordRow>
    );
  }

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      if (codeword) {
        const codewordLayout = renderCodeword(codeword, i);
        layoutArray.push(codewordLayout);
      }
    }
    return layoutArray;
  }

  return <>{renderCodewords()}</>;
};
