import React, { useRef, useState } from "react";
import { Button } from "../Button";
import { Icon } from "../Icon";
import { Typografy } from "../Typografy";
import {
  Container,
  ButtonsRow,
  Card,
  CodificationCards,
  FormRow,
  FormRowHeader,
  InputRow,
  ClosedContainer
} from "./style";
import codifications from "../../constants/codifications";
import { EncodingDecoding } from "../../enums/EncodingDecoding";
import { CodificationMethod } from "../../enums/CodificationMethod";
import { toast } from "react-toastify";
import {useOnProcessing, useCodificationMethod, useFinishedCodification, useFileToProcess} from '../../context'
const { ipcRenderer } = window.require('electron');


interface SideMenu {
  opened: boolean;
}

export const SideMenu = (props: SideMenu) => {
  const [codingDecoding, setCodingDecoding] = useState(EncodingDecoding.NO_ONE);
  const [codingMethod, setCodingMethod] = useCodificationMethod();
  const [file, setFile] = useState("");
  const [fileName, setFileName] = useState("");
  const [opened, setOpened] = useState(props.opened)
  const inputRef = useRef(null);
  const [, setFileToProcess] = useFileToProcess();
 
  const [, setOnProcessing] = useOnProcessing();
  const [, setOnFinishedCodification] = useFinishedCodification();

  function handleCodificationMode(codificationMode: number) {
    setCodingDecoding(codificationMode);
  }

  function handleCodificationMethod(codificationMethod: number) {
    setCodingMethod(codificationMethod);
  }

  function handleInput(e) {
    const files =e.target.files;
    console.log(files[0].path)
    
    if(files){
      setFile(files[0].path);
      setFileName(files[0].name);
    }   
  }

  async function inicialize() {
 
    if (codingDecoding == EncodingDecoding.NO_ONE) {
      toast.warn("Selecione o modo");
    } else if (codingMethod == CodificationMethod.NO_ONE) {
      toast.warn("Selecione um algoritmo");
    } else if (!file) {
      toast.warn("Selecione um arquivo");
    } else {
      setOnProcessing(true);

      const response = await ipcRenderer.invoke("coding", {
        codingDecoding, codingMethod, file
      })

      setOnFinishedCodification(true);

      if(response.ok){
        setOnProcessing(false)
        setFileToProcess(response.filesaved)
      }
    }
  }

  function clickOnLabel(){
    inputRef.current.click();
  }

  return (
    <>
     {!opened && (
       <ClosedContainer></ClosedContainer>
     )}
      {opened && (
        <Container>
          <FormRow>
            <FormRowHeader>
              <span className="form-index">1</span>
              <Typografy.EMPHASYS text="Modo" />
            </FormRowHeader>
            <ButtonsRow>
              <Button.PRIMARY
                isSelected={codingDecoding == EncodingDecoding.ENCODING}
                onClick={() => {
                  handleCodificationMode(EncodingDecoding.ENCODING);
                }}
              >
                Codificar
              </Button.PRIMARY>
              <Button.PRIMARY
                isSelected={codingDecoding == EncodingDecoding.DECODING}
                onClick={() => {
                  handleCodificationMode(EncodingDecoding.DECODING);
                }}
              >
                Decodificar
              </Button.PRIMARY>
            </ButtonsRow>
          </FormRow>

          <FormRow>
            <FormRowHeader>
              <span className="form-index">2</span>
              <Typografy.EMPHASYS text="Selecione a codificação" />
            </FormRowHeader>
            <CodificationCards>
              {codifications.map((codification) => {
                return (
                  <Card
                    onClick={() => handleCodificationMethod(codification.index)}
                    isSelected={codingMethod == codification.index}
                  >
                    <div className="card-image">{codification.image}</div>
                    <span className="card-name">{codification.name}</span>
                  </Card>
                );
              })}
            </CodificationCards>
          </FormRow>

          <FormRow>
            <FormRowHeader>
              <span className="form-index">3</span>
              <Typografy.EMPHASYS text="Selecione o arquivo" />
            </FormRowHeader>
            <InputRow>
              <input
                ref={inputRef}
                type="file"              
                onChange={handleInput}
              />
              <label onClick={clickOnLabel}>{fileName?fileName:"Nenhum arquivo selecionado"}</label>
              <Icon.Search color="#fff" size={20} />
            </InputRow>
          </FormRow>

          <FormRow>
            <FormRowHeader>
              <span className="form-index">4</span>
              <Typografy.EMPHASYS text="Faça a codificação" />
            </FormRowHeader>
            <ButtonsRow>
              <Button.CLICK onClick={inicialize}>
                Iniciar
              </Button.CLICK>
            </ButtonsRow>
          </FormRow>
        </Container>
      )}
    </>
  );
};
