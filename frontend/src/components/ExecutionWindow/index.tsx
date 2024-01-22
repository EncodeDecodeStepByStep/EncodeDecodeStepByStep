import React, { useEffect, useState } from "react";
import { Container } from "./style";

import {
  useOnProcessing,
  useFinishedCodification,
  useCodificationMethod,
  useCodewords,
  useIndex,
  useTheme,
  useCodingDecoding,
} from "../../context";

import { CodificationMethod } from "../../enums/CodificationMethod";
import { progress, nextStep } from "../../hooks/useCodification";
import { Codeword } from "../../models/codeword";
import codifications from "../../constants/codifications";
import { EncodingDecoding } from "../../enums/EncodingDecoding";
import { OnError } from "./OnError";
import { OnProcessing } from "./OnProcessing";
import { Steps } from "./Steps";

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
  const [, setCodingDecoding] = useCodingDecoding();
  const [index, setIndex] = useIndex();
  const [theme] = useTheme();

  const [actualPercentage, setActualPercentage] = useState(0);
  const [length, setLength] = useState(0);

  useEffect(() => {
    let interval;

    if (onProcessing) {
      interval = setInterval(async () => {
        try {
          const percentage = await progress();
          if (percentage < 60) {
            setActualPercentage(percentage);
          } else {
            endCodification();
          }
        } catch (e) {
          props.setOnError(true);
          endCodification();
        }
      }, 1000);
    }

    return () => {
      clearInterval(interval);
    };
  }, [onProcessing]);

  useEffect(() => {
    if (onFinishedCodification) {
      getFirstCodeword();
    }

    async function getFirstCodeword() {
      const codeword = await nextStep();

      if (!codeword) {
        return;
      }

      setLength(codeword.numberOfCharsTotal);

      if (!codeword.characterBeforeEncode) {
        if(codeword.codificationName.includes("Huffman Estático"))
          codeword.codificationName = "Static Huffman"; // TODO In the future translate inside the backend and remove these lines
        if(codeword.codificationName.includes("Unário"))
            codeword.codificationName = "Unary";
        const codificationFinded = codifications.filter((codification) =>
          codification.name.includes(codeword.codificationName)
        );
        setCodificationMethod(codificationFinded[0]);
      }

      addCodeword(codeword);
    }
  }, [onFinishedCodification]);

  async function next() {   
    if (index >= codewords.length) {
      const codeword = await nextStep();

      if (!codeword.codeword && !codeword.characterDecoded) {
        return;
      }
      addCodeword(codeword);
    }
    setIndex(index + 1);
  }

  function addCodeword(codeword) {
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
  }

  function back() {
    if (index > 0) {
      setIndex(index - 1);
    }
  }

  function finish() {
    props.setOnError(false);
    setActualPercentage(0);
    setLength(0);

    setOnProcessing(false);
    setOnFinishedCodification(false);
    setCodingDecoding(EncodingDecoding.NO_ONE);
    setCodificationMethod(CodificationMethod.NO_ONE);
    setCodewords([]);
    setIndex(1);
  }

  function endCodification() {
    setOnFinishedCodification(true);
    setOnProcessing(false);
  }

  return (
    <Container isDark={theme}>
      {props.onError && <OnError finish={finish} />}

      {!props.onError && onProcessing && (
        <OnProcessing finish={finish} actualPercentage={actualPercentage} />
      )}

      {!props.onError && onFinishedCodification && codificationMethod && (
        <Steps back={back} next={next} finish={finish} index={index} length={length}/>
      )}
    </Container>
  );
};
