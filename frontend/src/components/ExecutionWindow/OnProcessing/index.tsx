import React from "react";
import { useCodificationMethod, useTheme } from "../../../context";
import { Button } from "../../Button";
import { Typografy } from "../../Typografy";
import { Container } from "./styles";
import { Line } from "rc-progress";

export const OnProcessing = ({ actualPercentage, finish }) => {
  const [codificationMethod, setCodificationMethod] = useCodificationMethod();
  const [theme] = useTheme();
  return (
    <Container isDark={theme}>
      <Typografy.SUBTITLE
        text={
          codificationMethod.name
            ? `Processing ${codificationMethod.name}`
            : "Decoding"
        }
      ></Typografy.SUBTITLE>

      {actualPercentage > 0 && (
        <>
          <div className="percentage-value">
            <span> {actualPercentage.toFixed(2)} %</span>
            <Line
              percent={actualPercentage}
              strokeWidth={5}
              strokeColor="#49d280"
            />
          </div>
          <p className="waiting-message">
              Putting coding in the background
          </p>
        </>
      )}
      <Button.PRIMARY onClick={finish}>Cancel</Button.PRIMARY>
    </Container>
  );
};
