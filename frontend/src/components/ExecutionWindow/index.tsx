import React, { useEffect, useState } from "react";
import { Container, OnProcessing, Steps, Buttons, StepsCanva, ScroolingList } from "./style";

import {
  useOnProcessing,
  useFinishedCodification,
  useCodificationMethod,
  useFileToProcess
} from "../../context";
import { Typografy, Button} from "../index";
import { getCodificationNameByIndex } from "../../service/getCodificationNameByIndex";
import Loader from "react-loader-spinner";

import "react-loader-spinner/dist/loader/css/react-spinner-loader.css";
import { CodificationMethod } from "../../enums/CodificationMethod";
import { UnaryLayout } from "../CodificationLayouts";
import { Icon } from "../Icon";

const fs = window.require('fs');

export const ExecutionWindow = () => {
  const [onProcessing] = useOnProcessing();
  const [onFinishedCodification] = useFinishedCodification();
  const [codificationMethod] = useCodificationMethod();

  const [codificationChosen, setCodificationChosen] = useState("");
  const [index, setIndex] = useState(1);
  const [text, setText] = useState("");
  const [length, setLength] = useState(0);

  const [fileToProcess,] = useFileToProcess();

  useEffect(() => {
    const codigicationName = getCodificationNameByIndex(codificationMethod);
    setCodificationChosen(codigicationName);
  }, [codificationMethod]);

  useEffect(() => {
    if(onFinishedCodification && fileToProcess){
      const buffer = fs.readFileSync(fileToProcess,"utf-8");
      setText(buffer.toString());
    }
  }, [fileToProcess, onFinishedCodification]);

  function next(){
    console.log(index+" "+length)
    if(index<length-1){
      setIndex(index+1);
    }
  }

  function back(){
    if(index>0){
      setIndex(index-1);
    }
  }

  function finish(){
    setIndex(length-1);
  }

  return (
    <Container>
      {onProcessing && (
        <OnProcessing>
          <Typografy.SUBTITLE text={`Processando ${codificationChosen.name}`}></Typografy.SUBTITLE>
          <Loader
            type="TailSpin"
            color="rgb(73,210,128)"
            height={100}
            width={100}
          />
        </OnProcessing>
      )}

    {onFinishedCodification && (
        <Steps>
          <StepsCanva>
            <header>
              <Typografy.EMPHASYS className="codification-title" text={`Decodificando ${codificationChosen.name}`}/>
              <span className="counter">{index}/{length-1}</span>
            </header>
            <ScroolingList>
              {codificationMethod==CodificationMethod.UNARIO &&(
                <UnaryLayout text={text} index={index} setLength={setLength}/>
              )}
            </ScroolingList>
          </StepsCanva>
          <Buttons>
            <Button.PRIMARY 
              disabled={index==1}
              icon={<Icon.Back size={18} color="#fff"/>} 
              onClick={back}>Retroceder</Button.PRIMARY>
            <Button.PRIMARY 
              disabled={index==length-1}
              icon={<Icon.Next size={18} color="#fff"/>}
              onClick={next}>Avançar</Button.PRIMARY>
            <Button.PRIMARY 
              icon={<Icon.Finish size={18} color="#fff"/>}
              onClick={finish}>Finalizar</Button.PRIMARY>
          </Buttons>
        </Steps>
    )}
   
    </Container>
  );
};
