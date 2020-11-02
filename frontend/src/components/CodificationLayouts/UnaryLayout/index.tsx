import React from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { UnaryCodewordRow } from "./styles";
import { useCodewords, useIndex, useTheme } from "../../../context";
import { Codeword } from "../../../models/codeword";

export const UnaryLayout = () => {
  const [index, ] = useIndex();
  const [codewords,] = useCodewords();
  const [theme] = useTheme();

  function renderCodeword(codeword: Codeword) {
    if (codeword) {
      return (
        <UnaryCodewordRow isDark={theme}>
          <span className="codeword">{codeword.codeword}</span>
          <div>
            <span className="ascii">ASCII</span>
            <strong className="code">{codeword.codeword.length} ({codeword.codeword.charAt(0)}`s)</strong>
            <Icon.TransformTo size={15} color={PRIMARY} />
            <span className="codevalue">
              {codeword.value}
            </span>
          </div>
        </UnaryCodewordRow>
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
