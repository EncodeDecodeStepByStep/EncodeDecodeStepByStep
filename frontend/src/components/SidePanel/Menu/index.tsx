import React, { useRef, useState } from "react";
import { Button, Icon, Typografy } from "../../index";
import {
  ButtonsRow,
  Card,
  CodificationCards,
  FormRow,
  FormRowHeader,
  InputRow,
  Container
} from "./style";
import codifications from "../../../constants/codifications";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";
import { toast } from "react-toastify";
import { useOnProcessing, useCodificationMethod, useFinishedCodification, useGoulombDivisor } from '../../../context'
import { encode, decode } from '../../../hooks/useCodification'
import { CodificationMethod } from "../../../enums/CodificationMethod";
import { Codification } from '../../../models/codification'
import {useTheme} from '../../../context'

interface FileType {
  name?: string,
  path?: string
}

export const Menu = () => {
  const [codingDecoding, setCodingDecoding] = useState(EncodingDecoding.NO_ONE);
  const [codificationMethod, setCodificationMethod] = useCodificationMethod<Codification>();
  const [file, setFile] = useState<FileType>({});
  const [goulombDivisor, setGoulombDivisor] = useGoulombDivisor();

  const inputRef = useRef(null);
  const [theme,] = useTheme();
  console.log(theme)

  const [, setOnProcessing] = useOnProcessing();
  const [, setOnFinishedCodification] = useFinishedCodification();

  function handleCodificationMode(codificationMode: number) {
    if (codificationMode === EncodingDecoding.DECODING) {
      setCodificationMethod(-1)
    }
    setCodingDecoding(codificationMode);
  }

  function handleCodificationMethod(codificMethod: Codification) {
    setCodificationMethod(codificMethod);
  }

  function changeGoulombDividor(newDivisor: number) {
    if (newDivisor > 1) {
      setGoulombDivisor(newDivisor)
    }
  }

  function handleInput(e: any) {
    const files = e.target.files;
    if (files) {
      setFile(files[0]);
    }
  }

  async function inicialize() {

    if (codingDecoding === EncodingDecoding.NO_ONE) {
      toast.warn("Selecione o modo");
    } else if (codingDecoding!==EncodingDecoding.DECODING && !codificationMethod.codificationType) {
      toast.warn("Selecione um algoritmo");
    } else if (!file.path) {
      toast.warn("Selecione um arquivo");
    } else {
      setOnProcessing(true);
      setOnFinishedCodification(false);
      if (codingDecoding === EncodingDecoding.ENCODING) {
        await encode(codificationMethod.urlName, file.path, goulombDivisor);
      } else {
        await decode(file.path);
      }
    }
  }

  function clickOnLabel() {
    inputRef.current.click();      
  }

  function getIndexOfFileRow() {
    if (codingDecoding === EncodingDecoding.ENCODING) {
      return codificationMethod.codificationType === CodificationMethod.GOULOMB ? 4 : 3
    } else {
      return 2;
    }
  }

  function renderEncodingDecoding() {
    return (
      <FormRow>
        <FormRowHeader>
          <span className="form-index">1</span>
          <Typografy.EMPHASYS text="Modo" />
        </FormRowHeader>
        <ButtonsRow>
          <Button.PRIMARY
            isSelected={codingDecoding === EncodingDecoding.ENCODING}
            onClick={() => {
              handleCodificationMode(EncodingDecoding.ENCODING);
            }}>
            Codificar
          </Button.PRIMARY>
          <Button.PRIMARY
            isSelected={codingDecoding === EncodingDecoding.DECODING}
            onClick={() => {
              handleCodificationMode(EncodingDecoding.DECODING);
            }}>
            Decodificar
              </Button.PRIMARY>
        </ButtonsRow>
      </FormRow>
    )
  }

  function renderCodificationMethod() {
    if (codingDecoding === EncodingDecoding.ENCODING) {
      return (
        <FormRow>
          <FormRowHeader>
            <span className="form-index">2</span>
            <Typografy.EMPHASYS text="Selecione a codificação" />
          </FormRowHeader>
          <CodificationCards>
            {codifications.map((codification, index) => {
              return (
                <Card
                  key={index}
                  onClick={() => handleCodificationMethod(codification)}
                  isSelected={codificationMethod.codificationType === codification.codificationType}
                >
                  <div className="card-image">{codification.icon}</div>
                  <span className="card-name">{codification.name}</span>
                </Card>
              );
            })}
          </CodificationCards>
        </FormRow>
      )
    }
  }

  function renderGoulombOptions() {
    if (codificationMethod.codificationType === CodificationMethod.GOULOMB) {
      return (
        <FormRow>
          <FormRowHeader>
            <span className="form-index">3</span>
            <Typografy.EMPHASYS text="Selecione o divisor Goulomb" />
          </FormRowHeader>
          <InputRow>
            <input type="number" value={goulombDivisor} onChange={(e) => {
              changeGoulombDividor(parseInt(e.target.value))
            }} />
          </InputRow>
        </FormRow>
      )
    }
  }

  function renderInputFile() {
    return (
      <FormRow>
        <FormRowHeader>
          <span className="form-index">{getIndexOfFileRow()}</span>
          <Typografy.EMPHASYS text="Selecione o arquivo" />
        </FormRowHeader>
        <InputRow>
          <input
            ref={inputRef}
            type="file"
            onChange={handleInput}
          />
          <label onClick={clickOnLabel}>{file.name ? file.name : "Nenhum arquivo selecionado"}</label>
          <Icon.Search color="#fff" size={20} />
        </InputRow>
      </FormRow>
    )
  }

  function renderInitButton() {
    return (
      <FormRow>
        <FormRowHeader theme={theme}>
          <span className="form-index">{getIndexOfFileRow() + 1}</span>
          <Typografy.EMPHASYS text="Faça a codificação" />
        </FormRowHeader>
        <ButtonsRow>
          <Button.CLICK onClick={inicialize}>
            Iniciar
          </Button.CLICK>
        </ButtonsRow>
      </FormRow>
    )
  }

  return (
    <Container theme={theme}>
      {renderEncodingDecoding()}
      {renderCodificationMethod()}
      {renderGoulombOptions()}
      {renderInputFile()}
      {renderInitButton()}
    </Container>
  );
};
