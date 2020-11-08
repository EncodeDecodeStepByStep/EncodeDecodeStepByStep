import React from "react";
import { GRAY, PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { FibonacciCodewordRow } from "./styles";
import {
  useCodewords,
  useCodingDecoding,
  useIndex,
  useTheme,
} from "../../../context";
import { Codeword } from "../../../models/codeword";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";

export const FibonacciLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [theme] = useTheme();
  const [codingDecoding] = useCodingDecoding();

  function generateFibonacciSequence(quantity: number) {
    let fib = Array<number>();
    fib[0] = 0;
    fib[1] = 1;
    for (let i = 2; i < quantity + 1; i++) {
      fib[i] = fib[i - 2] + fib[i - 1];
    }

    fib.shift();
    fib.shift();
    return fib;
  }

  function fibonacciArrangement(cw: Codeword) {
    const { codeword, value } = cw;
    const arrangements = Array();
    const sumArray = new Array();
    const fibonacciSequence = generateFibonacciSequence(codeword.length);

    let sum = 0;

    if (codeword && fibonacciSequence) {
      for (let i = 0; i < codeword.length; i++) {
        let mark = false;
        if (codeword.charAt(i) === "1" && fibonacciSequence[i]) {
          mark = true;
          sum += fibonacciSequence[i];
          sumArray.push(fibonacciSequence[i]);
        }
        arrangements.push(
          <div className={`fibonacci-column ${mark && "mark"}`}>
            <span className="fibonacci-sequence-letter">
              {i + 1 == codeword.length ? (
                <span className="stop-bit">SB</span>
              ) : (
                fibonacciSequence[i]
              )}
            </span>
            <span className="divisor">
              <Icon.Down size={15} color={GRAY} />
            </span>
            <span className="codeword-letter">{codeword.charAt(i)}</span>
          </div>
        );
      }
    }

    return (
      <div className="fibonacci-arrangment">
        {codingDecoding === EncodingDecoding.DECODING ? (
          <>
            {renderFibonattiCodeword(arrangements)}
            {renderCountDemostration(sumArray, sum, value)}
          </>
        ) : (
          <>
            {renderCountDemostration(sumArray, sum, value)}
            {renderFibonattiCodeword(arrangements)}
          </>
        )}
      </div>
    );
  }

  function renderFibonattiCodeword(arrangements) {
    return (
      <div className="fibonatti-row">
        <div className="fibonacci-column">
          <span>Fibonacci</span>
          <span>
            <Icon.Down size={15} color={GRAY} />
          </span>
          <span>Codeword</span>
        </div>
        {[...arrangements]}
      </div>
    );
  }

  function renderCountDemostration(sumArray, sum, value) {
    return (
      <div className="explanation-row">
        {codingDecoding === EncodingDecoding.DECODING ? (
          <>
            {renderCalculus(sumArray, sum)}= &nbsp;
            <div className="equality">
              <span className="ascii">ASCII</span>
              <strong className="code">{sum - 1}</strong>
              <div className="icon-container">
                <Icon.TransformTo size={15} color={PRIMARY} />
              </div>

              <span className="codevalue">{value}</span>
            </div>
          </>
        ) : (
          <>
            <div className="equality">
              <span className="codevalue">{value}</span>
              <div className="icon-container">
                <Icon.TransformTo size={15} color={PRIMARY} />
              </div>
              <strong className="code">{sum - 1}</strong>
              <span className="ascii">(ASCII)</span>
            </div>
            = &nbsp;
            {renderCalculus(sumArray, sum)}
          </>
        )}
      </div>
    );
  }

  function renderCalculus(sumArray, sum) {
    return (
      <div className="count">
        {sumArray.map((num, index) => {
          return (
            <>
              <span>
                <strong>{num}</strong>
              </span>
              &nbsp;{index === sumArray.length - 1 ? "=" : "+"}&nbsp;
            </>
          );
        })}
        {sum} &nbsp;
        <span className="one-difference">
          (Diferença de 1, por causa do incremento)
        </span>
        &nbsp;
      </div>
    );
  }

  function renderCodeword(codeword: Codeword) {
    if (codeword) {
      return (
        <FibonacciCodewordRow isDark={theme}>
          {fibonacciArrangement(codeword)}
        </FibonacciCodewordRow>
      );
    }
  }

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];

      layoutArray.push(renderCodeword(codeword));
    }
    return layoutArray;
  }

  return <>{renderCodewords()}</>;
};
