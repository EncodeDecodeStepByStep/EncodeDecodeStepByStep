import React from "react";
import {
  useCodificationMethod,
  useCodingDecoding,
  useTheme,
} from "../../../context";
import { CodificationMethod } from "../../../enums/CodificationMethod";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";
import { Typografy } from "../../Typografy";
import { Container, StepsCanva, ScroolingList, Buttons } from "./styles";
import {
  DeltaLayout,
  EliasGammaLayout,
  FibonacciLayout,
  GoulombLayout,
  UnaryLayout,
  HuffmanLayout,
  HammingLayout,
} from "../../CodificationLayouts";
import { Button } from "../../Button";
import { Icon } from "../../Icon";

export const Steps = ({ index, length, back, next, finish }) => {
  const [theme] = useTheme();
  const [codingDecoding] = useCodingDecoding();
  const [codificationMethod] = useCodificationMethod();

  function renderCodificationLayout() {
    switch (codificationMethod.codificationType) {
      case CodificationMethod.UNARIO:
        return <UnaryLayout />;
      case CodificationMethod.ELIAS_GAMMA:
        return <EliasGammaLayout />;
      case CodificationMethod.GOULOMB:
        return <GoulombLayout />;
      case CodificationMethod.FIBONACCI:
        return <FibonacciLayout />;
      case CodificationMethod.DELTA:
        return <DeltaLayout />;
      case CodificationMethod.HUFFMAN:
        return <HuffmanLayout />;
      case CodificationMethod.HAMMING:
        return <HammingLayout />;
    }
  }

  return (
    <Container>
      <StepsCanva isDark={theme}>
        <header>
          <Typografy.EMPHASYS
            className="codification-title"
            text={`Transcoding ${codificationMethod.name}`}
          />
          {codingDecoding === EncodingDecoding.ENCODING && (
            <span className="counter">
              {index}/{length}
            </span>
          )}
        </header>
        <ScroolingList
          scrool={
            codificationMethod.codificationType !== CodificationMethod.HUFFMAN
          }
        >
          {renderCodificationLayout()}
        </ScroolingList>
      </StepsCanva>

      <Buttons>
        <Button.PRIMARY
          isDark={theme}
          disabled={index === 1}
          icon={<Icon.Back size={18} color="#fff" />}
          onClick={back}
        >
          Go Back
        </Button.PRIMARY>
        <Button.PRIMARY
          isDark={theme}
          disabled={index >= length}
          icon={<Icon.Next size={18} color="#fff" />}
          onClick={next}
        >
          Next
        </Button.PRIMARY>
        <Button.PRIMARY
          isDark={theme}
          icon={<Icon.Close size={18} color="#fff" />}
          onClick={finish}
        >
          Exit
        </Button.PRIMARY>
      </Buttons>
    </Container>
  );
};
