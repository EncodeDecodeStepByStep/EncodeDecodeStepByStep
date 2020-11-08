import React from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import {
  DeltaCodewordRow,
  CodewordLayout,
  CodewordCount,
  SameCodeword,
} from "./styles";
import {
  useCodewords,
  useCodingDecoding,
  useIndex,
  useTheme,
} from "../../../context";
import { Codeword } from "../../../models/codeword";
import { Typografy } from "../../Typografy";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";

export const DeltaLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [theme] = useTheme();

  const [codingDecoding] = useCodingDecoding();

  function renderDeltaDontChange(codeword: Codeword, index:number) {
    return (
      <DeltaCodewordRow key={index} isDark={theme}>
        <Typografy.EMPHASYS
          className="delta-codeword-title"
          text="Mesmo simbolo que o anterior"
        />
        <SameCodeword>
          <span className="codeword">{codeword.codeword}</span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span className="codevalue">{codeword.value}</span>
        </SameCodeword>
      </DeltaCodewordRow>
    );
  }

  function renderCodeword(changedBit, signalBit, difference) {
    return (
      <CodewordLayout isDark={theme}>
        <div>
          <span>{changedBit}</span>
          <Icon.Changed color={theme ? PRIMARY : "black"} size={18} />
        </div>

        <div>
          <span>{signalBit}</span>
          {signalBit === "1" ? (
            <Icon.Minus color={theme ? PRIMARY : "black"} size={18} />
          ) : (
            <Icon.Plus color={theme ? PRIMARY : "black"} size={18} />
          )}
        </div>

        <div>
          <span>{difference}</span>
          <Icon.Delta color={theme ? PRIMARY : "black"} size={18} />
        </div>
      </CodewordLayout>
    );
  }

  function renderCodewordCount(asciiAnterior, signalBit, difference, value) {
    return (
      <CodewordCount>
        <span>{asciiAnterior}[Ascii Anterior]</span>
        {signalBit === "1" ? (
          <Icon.Minus color={theme ? PRIMARY : "black"} size={18} />
        ) : (
          <Icon.Plus color={theme ? PRIMARY : "black"} size={18} />
        )}
        <span>{parseInt(difference, 2) + 1} [Delta]</span>
        <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
        <span>
          {signalBit === "1"
            ? parseInt(difference, 2) - 1 + asciiAnterior
            : parseInt(difference, 2) + 1 + asciiAnterior}
        </span>
        <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
        <span>{value}</span>
      </CodewordCount>
    );
  }

  function renderDeltaChange(codewordObject: Codeword, actualIndex: number) {
    const { codeword, value } = codewordObject;
    const changedBit = codeword.charAt(0);
    const signalBit = codeword.charAt(1);
    const difference = codeword.substring(2);
    const asciiAnterior = codewords[actualIndex - 1].value.charCodeAt(0);
    return (
      <DeltaCodewordRow key={actualIndex} isDark={theme}>
        <Typografy.EMPHASYS
          className="delta-codeword-title"
          text="Trocou o simbolo"
        />
        {codingDecoding === EncodingDecoding.DECODING ? (
          <>
            {renderCodeword(changedBit, signalBit, difference)}
            {renderCodewordCount(asciiAnterior, signalBit, difference, value)}
          </>
        ) : (
          <>
            {renderCodewordCount(asciiAnterior, signalBit, difference, value)}
            {renderCodeword(changedBit, signalBit, difference)}
          </>
        )}
      </DeltaCodewordRow>
    );
  }

  function renderFirstCodeword(codewordObject: Codeword, index:number) {
    const { codeword, value } = codewordObject;
    return (
      <DeltaCodewordRow key={index} isDark={theme}>
        <div>
          <Typografy.EMPHASYS
            className="delta-codeword-title"
            text="Primeiro simbolo"
          />
        </div>
        <div className="first-codeword">
          {codingDecoding === EncodingDecoding.DECODING ? (
            <>
              <span className="codeword">{codeword.substring(5)}</span>
              <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
              <span>{parseInt(codeword.substring(5), 2)}</span>
              <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
              <span className="codevalue">{value}</span>
            </>
          ) : (
            <>
              <span className="codevalue">{value}</span>
              <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
              <span>{parseInt(codeword, 2)}</span>
              <Icon.TransformTo size={15} color={theme ? PRIMARY : "black"} />
              <span className="codeword">{codeword}</span>
            </>
          )}
        </div>
      </DeltaCodewordRow>
    );
  }

  function renderCodewords() {
    const layoutArray = [];
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      if (codeword) {
        let layout;
        if (i === 0) {
          layout = renderFirstCodeword(codeword, i);
        } else {
          layout =
            codeword.codeword.length === 1
              ? renderDeltaDontChange(codeword, i)
              : renderDeltaChange(codeword, i);
        }

        layoutArray.push(layout);
      }
    }
    return layoutArray;
  }

  return <>{renderCodewords()}</>;
};
