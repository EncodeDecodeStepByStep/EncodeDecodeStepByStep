import React from "react";
import { useIndex, useCodewords, useTheme } from "../../../context";
import { Typografy } from "../../Typografy";
import { Container } from "./style";

export const Text = () => {
  const [index] = useIndex();
  const [codewords] = useCodewords();
  const [theme] = useTheme();

  return (
    <Container isDark={theme}>
      <Typografy.SUBTITLE className="text-title" text="Text" />
      <p>
        {codewords.map((codeword, i) => {
          const simbol = codeword.value;
          if (i === index - 1) {
            return <span className="actual-symbol">{simbol}</span>;
          } else {
            return <span>{simbol}</span>;
          }
        })}
      </p>
    </Container>
  );
};
