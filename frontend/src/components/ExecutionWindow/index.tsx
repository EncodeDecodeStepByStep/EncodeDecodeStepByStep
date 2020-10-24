import React, { useEffect, useState } from "react";
import { Container, OnProcessing, Steps, Buttons, StepsCanva, ScroolingList, OnError } from "./style";

import {
  useOnProcessing,
  useFinishedCodification,
  useCodificationMethod,
  useCodewords,
  useIndex,
} from "../../context";
import { Typografy, Button } from "../index";

import { CodificationMethod } from "../../enums/CodificationMethod";
import { DeltaLayout, EliasGammaLayout, FibonacciLayout, GoulombLayout, UnaryLayout, HuffmanLayout } from "../CodificationLayouts";
import { Icon } from "../Icon";
import { progress, nextStep } from '../../hooks/useCodification'
import { Line } from 'rc-progress';
import { Codeword } from "../../models/codeword";
import ErrorGif from '../../assets/error.gif'
import codifications from "../../constants/codifications";

interface ExecutionWindowProps{
  onError:boolean;
  setOnError:Function;
}

export const ExecutionWindow = (props:ExecutionWindowProps) => {
  const [onProcessing, setOnProcessing] = useOnProcessing();
  const [onFinishedCodification, setOnFinishedCodification] = useFinishedCodification();
  const [codificationMethod, setCodificationMethod] = useCodificationMethod();
  const [codewords, setCodewords] = useCodewords();
  
  const [actualPercentage, setActualPercentage] = useState(0)

  const [index, setIndex] = useIndex();
  const [length, setLength] = useState(0);

  function finishCodification(interval){
    clearInterval(interval)
    setOnFinishedCodification(true);
    setOnProcessing(false)
  }

  useEffect(() => {
    if (onProcessing) {
      let interval = setInterval(async () => {
        try{
          const percentage = await progress();
          if (percentage < 100) {
            setActualPercentage(percentage)
          } else {
            finishCodification(interval)
          }
        }catch(e){
          props.setOnError(true);
         finishCodification(interval);
        }
      }, 1000);
    }
  }, [onProcessing])

  useEffect(() => {
    if (onFinishedCodification) {
      getFirstCodeword()
    }

    async function getFirstCodeword() {
      const codeword = await nextStep()
      if(codeword){
        setLength(codeword.numberOfCharsTotal)
        if(codeword.characterBeforeEncode){
          setCodewords([new Codeword(codeword.characterBeforeEncode, codeword.codeword)])
        }else{
          const codificationFinded = codifications.filter(codification => codification.name.includes(codeword.codificationName))
          setCodificationMethod(codificationFinded[0])
          setCodewords([new Codeword(codeword.characterDecoded, codeword.bitsBeforeDecode)])
        }        
      }      
    }
  }, [onFinishedCodification]);

  async function next() {
    const codeword = await nextStep()
    if(codeword.characterBeforeEncode){
      setCodewords([...codewords, new Codeword(codeword.characterBeforeEncode, codeword.codeword)])
    }else{
      setCodewords([...codewords, new Codeword(codeword.characterDecoded, codeword.bitsBeforeDecode)])
    }
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
    setOnProcessing(false);
    setOnFinishedCodification(false);
    setCodificationMethod(-1)
    setCodewords([])
    setActualPercentage(0)
    setLength(0);
    setIndex(1);
  }

  function renderCodificationLayout(){
    switch(codificationMethod.codificationType){
      case CodificationMethod.UNARIO:
        return <UnaryLayout />
      case CodificationMethod.ELIAS_GAMMA:
        return <EliasGammaLayout />
      case CodificationMethod.GOULOMB:
        return <GoulombLayout />
      case CodificationMethod.FIBONACCI:
        return <FibonacciLayout />
      case CodificationMethod.DELTA:
        return <DeltaLayout />
      case CodificationMethod.HUFFMAN:
        return <HuffmanLayout />
    }
  }

  return (
    <Container>
      {
        props.onError && (
          <OnError>
              <img alt="error" src={ErrorGif}/>
              <Typografy.SUBTITLE text="Ops, algo deu errado"/>
              <p>Não foi possível se conectar ao servidor</p>
          </OnError>
        )
      }
      {!props.onError && onProcessing && (
        <OnProcessing>
          <Typografy.SUBTITLE text={`Processando ${codificationMethod.name}`}></Typografy.SUBTITLE>

          <Line percent={actualPercentage} strokeWidth={5} strokeColor="#49d280" />
          {actualPercentage && <div className="percentage-value">{actualPercentage.toFixed(2)} %</div>}

        </OnProcessing>
      )}

      {!props.onError && onFinishedCodification && (
        <Steps>
          <StepsCanva>
            <header>
              <Typografy.EMPHASYS className="codification-title" text={`Decodificando ${codificationMethod.name}`} />
              <span className="counter">{index}/{length - 1}</span>
            </header>
            <ScroolingList>
              { renderCodificationLayout()}              
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
              icon={<Icon.Close size={18} color="#fff" />}
              onClick={finish}>Sair</Button.PRIMARY>
          </Buttons>
        </Steps>
      )}
    </Container>
  );
};
