import React from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import {
  DeltaCodewordRow,
  CodewordLayout,
  CodewordCount,
  SameCodeword,
} from "./styles";
import { useCodewords, useIndex } from "../../../context";
import { Codeword } from "../../../models/codeword";
import { Typografy } from "../../Typografy";

export const DeltaLayout = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();

  function renderDeltaDontChange(codeword: Codeword) {
    return (
      <DeltaCodewordRow>
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

  function renderDeltaChange(codewordObject: Codeword, actualIndex: number) {
    const { codeword, value } = codewordObject;
    const changedBit = codeword.charAt(0);
    const signalBit = codeword.charAt(1);
    const difference = codeword.substring(2);
    const asciiAnterior = codewords[actualIndex - 1].value.charCodeAt(0);
    return (
      <DeltaCodewordRow>
        <Typografy.EMPHASYS
          className="delta-codeword-title"
          text="Trocou o simbolo"
        />
        <CodewordLayout>
          <div>
            <span>{changedBit}</span>
            <Icon.Changed color={PRIMARY} size={18} />
          </div>

          <div>
            <span>{signalBit}</span>
            {signalBit === "1" ? (
              <Icon.Minus color={PRIMARY} size={18} />
            ) : (
              <Icon.Plus color={PRIMARY} size={18} />
            )}
          </div>

          <div>
            <span>{difference}</span>
            <Icon.Delta color={PRIMARY} size={18} />
          </div>
        </CodewordLayout>

        <CodewordCount>
          <span>{asciiAnterior}[Ascii Anterior]</span>
          {signalBit === "1" ? (
            <Icon.Minus color={PRIMARY} size={18} />
          ) : (
            <Icon.Plus color={PRIMARY} size={18} />
          )}
          <span>{parseInt(difference, 2) + 1} [Delta]</span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span>
            {signalBit === "1"
              ? parseInt(difference, 2) - 1 + asciiAnterior
              : parseInt(difference, 2) + 1 + asciiAnterior}
          </span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span>{value}</span>
        </CodewordCount>
      </DeltaCodewordRow>
    );
  }

  function renderFirstCodeword(codewordObject: Codeword) {
    const { codeword, value } = codewordObject;
    return (
      <DeltaCodewordRow>
        <div>
          <Typografy.EMPHASYS
            className="delta-codeword-title"
            text="Primeiro simbolo"
          />
        </div>
        <div className="first-codeword">
          <span className="codeword">{codeword}</span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span>{parseInt(codeword, 2)}</span>
          <Icon.TransformTo size={15} color={PRIMARY} />
          <span className="codevalue">{value}</span>
        </div>
      </DeltaCodewordRow>
    );
  }

  function renderCodewords() {
    const layoutArray = Array();
    for (let i = 0; i < index; i++) {
      let codeword = codewords[i];
      if (codeword) {
        let layout;
        if (i == 0) {
          layout = renderFirstCodeword(codeword);
        } else {
          layout =
            codeword.codeword.length == 1
              ? renderDeltaDontChange(codeword)
              : renderDeltaChange(codeword, i);
        }

        layoutArray.push(layout);
      }
    }
    return layoutArray;
  }

  return <>{renderCodewords()}</>;
};
