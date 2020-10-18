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
  InputRow
} from "./style";
import codifications from "../../constants/codifications";
import { EncodingDecoding } from "../../enums/EncodingDecoding";
import { toast } from "react-toastify";
import { useOnProcessing, useCodificationMethod, useFinishedCodification } from '../../context'
import { encode, decode } from '../../hooks/useCodification'
import { CodificationMethod } from "../../enums/CodificationMethod";
import { Codification } from '../../models/codification'

interface FileType {
  name?: string,
  path?: string
}

export const SideMenu = () => {
  const [codingDecoding, setCodingDecoding] = useState(EncodingDecoding.NO_ONE);
  const [codificationMethod, setCodificationMethod] = useCodificationMethod<Codification>(-1);
  const [file, setFile] = useState<FileType>({});
  const [goulombDivisor, setGoulombDivisor] = useState(4);

  const inputRef = useRef(null);

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
    } else if (codificationMethod === -1) {
      toast.warn("Selecione um algoritmo");
    } else if (!file) {
      toast.warn("Selecione um arquivo");
    } else {
      setOnProcessing(true);
      setOnFinishedCodification(false);
      if (codingDecoding === EncodingDecoding.ENCODING) {
        await encode(codificationMethod.urlName, file.path);
      } else {
        await decode(codificationMethod.urlName, file.path);
      }
    }
  }

  function clickOnLabel() {
    inputRef.current.click();
  }

  return (
    <Container>
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
            }}
          >
            Codificar
              </Button.PRIMARY>
          <Button.PRIMARY
            isSelected={codingDecoding === EncodingDecoding.DECODING}
            onClick={() => {
              handleCodificationMode(EncodingDecoding.DECODING);
            }}
          >
            Decodificar
              </Button.PRIMARY>
        </ButtonsRow>
      </FormRow>

      {
        codingDecoding === EncodingDecoding.ENCODING &&
        <FormRow>
          <FormRowHeader>
            <span className="form-index">2</span>
            <Typografy.EMPHASYS text="Selecione a codificação" />
          </FormRowHeader>
          <CodificationCards>
            {codifications.map((codification) => {
              return (
                <Card
                  key={codificationMethod.type}
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
      }

      {
        codificationMethod.index === CodificationMethod.GOULOMB &&
        <FormRow>
          <FormRowHeader>
            <span className="form-index">2</span>
            <Typografy.EMPHASYS text="Selecione a codificação" />
          </FormRowHeader>
          <input type="number" value={goulombDivisor} onChange={(e) => {
            changeGoulombDividor(parseInt(e.target.value))
          }} />
        </FormRow>
      }


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
          <label onClick={clickOnLabel}>{file.name ? file.name : "Nenhum arquivo selecionado"}</label>
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
  );
};
