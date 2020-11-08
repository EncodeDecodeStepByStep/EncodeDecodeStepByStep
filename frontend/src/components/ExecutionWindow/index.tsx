import React, { useEffect, useState } from "react";
import {
  Container,
  OnProcessing,
  Steps,
  Buttons,
  StepsCanva,
  ScroolingList,
  OnError,
} from "./style";

import {
  useOnProcessing,
  useFinishedCodification,
  useCodificationMethod,
  useCodewords,
  useIndex,
  useTheme,
} from "../../context";
import { Typografy, Button } from "../index";

import { CodificationMethod } from "../../enums/CodificationMethod";
import {
  DeltaLayout,
  EliasGammaLayout,
  FibonacciLayout,
  GoulombLayout,
  UnaryLayout,
  HuffmanLayout,
  HammingLayout,
} from "../CodificationLayouts";
import { Icon } from "../Icon";
import { progress, nextStep } from "../../hooks/useCodification";
import { Line } from "rc-progress";
import { Codeword } from "../../models/codeword";
import ErrorGif from "../../assets/error.gif";
import codifications from "../../constants/codifications";
import messages from "./messages";

interface ExecutionWindowProps {
  onError: boolean;
  setOnError: Function;
}

export const ExecutionWindow = (props: ExecutionWindowProps) => {
  const [onProcessing, setOnProcessing] = useOnProcessing();
  const [
    onFinishedCodification,
    setOnFinishedCodification,
  ] = useFinishedCodification();
  const [codificationMethod, setCodificationMethod] = useCodificationMethod();
  const [codewords, setCodewords] = useCodewords();

  const [actualPercentage, setActualPercentage] = useState(0);

  const [index, setIndex] = useIndex();
  const [length, setLength] = useState(0);
  const [messageIndex, setMessageIndex] = useState(0);
  const [theme] = useTheme();

  let messageInterval, interval;

  function finishCodification() {
    clearInterval(interval);
    clearInterval(messageInterval);
    setOnFinishedCodification(true);
    setOnProcessing(false);
  }

  useEffect(() => {
    if (onProcessing) {
      messageInterval = setInterval(async () => {
        setMessageIndex((m) => (m + 1 == messages.length ? 0 : m + 1));
      }, 8000);

      interval = setInterval(async () => {
        try {
          const percentage = await progress();
          if (percentage < 60) {
            setActualPercentage(percentage);
          } else {
            finishCodification();
          }
        } catch (e) {
          props.setOnError(true);
          finishCodification();
        }
      }, 1000);
    }
  }, [onProcessing]);

  useEffect(() => {
    if (onFinishedCodification) {
      getFirstCodeword();
    }

    async function getFirstCodeword() {
      const codeword = await nextStep();
      if (codeword) {
        setLength(codeword.numberOfCharsTotal);
        if (codeword.characterBeforeEncode) {
          setCodewords([
            new Codeword(codeword.characterBeforeEncode, codeword.codeword),
          ]);
        } else {
          const codificationFinded = codifications.filter((codification) =>
            codification.name.includes(codeword.codificationName)
          );
          setCodificationMethod(codificationFinded[0]);
          setCodewords([
            new Codeword(codeword.characterDecoded, codeword.bitsBeforeDecode),
          ]);
        }
      }
    }
  }, [onFinishedCodification]);

  async function next() {
    const codeword = await nextStep();
    if(!codeword.codeword && !codeword.characterDecoded){
      return;
    }
    if (codeword.characterBeforeEncode) {
      setCodewords([
        ...codewords,
        new Codeword(codeword.characterBeforeEncode, codeword.codeword),
      ]);
    } else {
      setCodewords([
        ...codewords,
        new Codeword(codeword.characterDecoded, codeword.bitsBeforeDecode),
      ]);
    }
    if (index < length) {
      setIndex(index + 1);
    }
  }

  function back() {
    if (index > 0) {
      setIndex(index - 1);
    }
  }

  function finish() {
    setOnProcessing(false);
    setOnFinishedCodification(false);
    setCodificationMethod(-1);
    setCodewords([]);
    setActualPercentage(0);
    setLength(0);
    setIndex(1);
  }

  function cancelProcessing() {
    finishCodification();
    finish();
  }

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
    <Container isDark={theme}>
      {props.onError && (
        <OnError>
          <img alt="error" src={ErrorGif} />
          <Typografy.SUBTITLE text="Ops, algo deu errado" />
          <p>Não foi possível se conectar ao servidor</p>
        </OnError>
      )}
      {!props.onError && onProcessing && (
        <OnProcessing>
          <Typografy.SUBTITLE
            text={codificationMethod.name? `Processando ${codificationMethod.name}` : 'Decodificando'}
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
              <p>{messages[messageIndex]}</p>
              <Button.PRIMARY onClick={cancelProcessing}>
                Cancelar
              </Button.PRIMARY>
            </>
          )}
        </OnProcessing>
      )}

      {!props.onError && onFinishedCodification && codificationMethod && (
        <Steps>
          <StepsCanva>
            <header>
              <Typografy.EMPHASYS
                className="codification-title"
                text={`Decodificando ${codificationMethod.name}`}
              />
              <span className="counter">
                {index}/{length}
              </span>
            </header>
            <ScroolingList
              scrool={
                codificationMethod.codificationType !==
                CodificationMethod.HUFFMAN
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
              Retroceder
            </Button.PRIMARY>
            <Button.PRIMARY
              isDark={theme}
              disabled={index >= length}
              icon={<Icon.Next size={18} color="#fff" />}
              onClick={next}
            >
              Avançar
            </Button.PRIMARY>
            <Button.PRIMARY
              isDark={theme}
              icon={<Icon.Close size={18} color="#fff" />}
              onClick={finish}
            >
              Sair
            </Button.PRIMARY>
          </Buttons>
        </Steps>
      )}
    </Container>
  );
};
