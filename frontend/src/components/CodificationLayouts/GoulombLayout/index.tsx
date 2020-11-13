import React, { useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { GoulombCodewordRow, Explanation } from "./styles";
import {
  useCodewords,
  useIndex,
  useGoulombDivisor,
  useTheme,
  useCodingDecoding,
} from "../../../context";
import { Codeword } from "../../../models/codeword";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";

export const GoulombLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [goulombDivisor] = useGoulombDivisor();
  const [codingDecoding] = useCodingDecoding();
  const [theme] = useTheme();

  function renderExplanation(codeword: Codeword) {
    const codewordString = codeword.codeword;
    const stopBitPosition = getStopBitPosition(codewordString);
    const quantityOfZeros = codewordString.substring(0, stopBitPosition).length;

    const rest = codewordString.substring(stopBitPosition + 1);
    const binaryRest = parseInt(rest, 2);

    const integerPart = goulombDivisor * quantityOfZeros;
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
              {renderResult(
                goulombDivisor,
                quantityOfZeros,
                binaryRest,
                finalResult
              )}
            </>
          ) : (
            <>
              {renderResult(
                goulombDivisor,
                quantityOfZeros,
                binaryRest,
                finalResult
              )}

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
            <span className="ascii">ASCII {finalResult}</span>
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
            <span className="ascii">{finalResult}(Ascii)</span>
          </>
        )}
      </div>
    );
  }

  function renderResult(
    goulombDivisor,
    quantityOfZeros,
    binaryRest,
    finalResult
  ) {
    return (
      <span className="result">
        {goulombDivisor}
        [K] * &nbsp;
        <strong>{quantityOfZeros}</strong>
        &nbsp; + &nbsp;
        <strong>{binaryRest}</strong>
        &nbsp; = &nbsp;
        <strong>{finalResult}</strong>
      </span>
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
          &nbsp; [<strong>{binaryRest}</strong> em binário]
        </span>
      </>
    );
  }

  function getStopBitPosition(codeword:string){
    let position = 0;
    for(let i =0 ;i< codeword.length;i++){
      const character = codeword.charAt(i);
      if(character==='0'){
        position++;
      }else{
        break;
      }
    }
    return position;
  }

  function renderCodewordSplitted(codeword: string) {
    const stopBitPosition = getStopBitPosition(codeword);
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
      <GoulombCodewordRow isDark={theme} key={index}>
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

  return <>{renderCodewords()}</>;
};
