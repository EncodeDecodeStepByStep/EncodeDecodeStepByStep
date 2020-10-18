import React, { useEffect, useState } from "react";
import { Container, OnProcessing, Steps, Buttons, StepsCanva, ScroolingList } from "./style";

import {
  useOnProcessing,
  useFinishedCodification,
  useCodificationMethod,
} from "../../context";
import { Typografy, Button } from "../index";

import { CodificationMethod } from "../../enums/CodificationMethod";
import { UnaryLayout } from "../CodificationLayouts";
import { Icon } from "../Icon";
import { progress, nextStep } from '../../hooks/useCodification'
import { Line } from 'rc-progress';
import CodewordEncodingResponse from "../../models/codewordEncodingResponse";

export const ExecutionWindow = () => {
  const [onProcessing, setOnProcessing] = useOnProcessing();
  const [onFinishedCodification, setOnFinishedCodification] = useFinishedCodification();
  const [codificationMethod] = useCodificationMethod();

  const [actualPercentage, setActualPercentage] = useState(0)

  const [index, setIndex] = useState(1);
  const [codewords, setCodewords] = useState<CodewordEncodingResponse[]>([]);
  const [length, setLength] = useState(0);

  useEffect(() => {
    if (onProcessing) {
      let interval = setInterval(async () => {
        const percentage = await progress('unary');
        if (percentage < 100) {
          setActualPercentage(percentage)
        } else {
          clearInterval(interval)
          setOnFinishedCodification(true);
          setOnProcessing(false)
        }
      }, 1000);
    }
  }, [onProcessing])

  useEffect(() => {
    if (onFinishedCodification) {
      getFirstCodeword()
    }

    async function getFirstCodeword() {
      const codeword = await nextStep(codificationMethod.urlName)

      setLength(codeword.numberOfCharsTotal)
      setCodewords([...codewords, new CodewordEncodingResponse(codeword.codeword)])
    }
  }, [onFinishedCodification]);

  async function next() {
    const codeword = await nextStep(codificationMethod.urlName)
    setCodewords([...codewords, new CodewordEncodingResponse(codeword.codeword)])
    console.log(codeword)
    if (index < length - 1) {
      setIndex(index + 1);
    }
  }

  function back() {
    if (index > 0) {
      setIndex(index - 1);
    }
  }

  function finish() {
    setIndex(length - 1);
  }

  return (
    <Container>
      {onProcessing && (
        <OnProcessing>
          <Typografy.SUBTITLE text={`Processando ${codificationMethod.name}`}></Typografy.SUBTITLE>

          <Line percent={actualPercentage} strokeWidth={5} strokeColor="#49d280" />
          {actualPercentage && <div className="percentage-value">{actualPercentage.toFixed(2)} %</div>}

        </OnProcessing>
      )}

      {onFinishedCodification && (
        <Steps>
          <StepsCanva>
            <header>
              <Typografy.EMPHASYS className="codification-title" text={`Decodificando ${codificationMethod.name}`} />
              <span className="counter">{index}/{length - 1}</span>
            </header>
            <ScroolingList>
              {codificationMethod === CodificationMethod.UNARIO && (
                <UnaryLayout code={codewords} index={index} />
              )}
            </ScroolingList>
          </StepsCanva>
          <Buttons>
            <Button.PRIMARY
              disabled={index === 1}
              icon={<Icon.Back size={18} color="#fff" />}
              onClick={back}>Retroceder</Button.PRIMARY>
            <Button.PRIMARY
              disabled={index === length - 1}
              icon={<Icon.Next size={18} color="#fff" />}
              onClick={next}>Avançar</Button.PRIMARY>
            <Button.PRIMARY
              icon={<Icon.Finish size={18} color="#fff" />}
              onClick={finish}>Finalizar</Button.PRIMARY>
          </Buttons>
        </Steps>
      )}

    </Container>
  );
};
