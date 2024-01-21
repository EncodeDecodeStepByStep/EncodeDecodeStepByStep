import React, { useRef, useState } from "react";
import { Button, Icon, Typografy, Modal } from "../../index";
import {
  ButtonsRow,
  Card,
  CodificationCards,
  FormRow,
  FormRowHeader,
  InputRow,
  Container,
  DevCodification,
  ModalContainer
} from "./style";
import codifications from "../../../constants/codifications";
import { EncodingDecoding } from "../../../enums/EncodingDecoding";
import { toast } from "react-toastify";
import { useOnProcessing, useCodificationMethod, useFinishedCodification, useGoulombDivisor, useCodingDecoding } from '../../../context'
import { encode, decode } from '../../../hooks/useCodification'
import { CodificationMethod } from "../../../enums/CodificationMethod";
import { Codification } from '../../../models/codification'
import {useTheme} from '../../../context'
import { PRIMARY } from "../../../constants/colors";
import { useModal } from "../../../hooks";

interface FileType {
  name?: string,
  path?: string
}

export const Menu = () => {
  const [codingDecoding, setCodingDecoding] = useCodingDecoding();
  const [codificationMethod, setCodificationMethod] = useCodificationMethod<Codification>();
  const [file, setFile] = useState<FileType>({});
  const [goulombDivisor, setGoulombDivisor] = useGoulombDivisor();
  const [explanation, setExplanation] = useState(null);

  const inputRef = useRef(null);
  const [theme,] = useTheme();
  const { isShown, toggle } = useModal();

  const [, setOnProcessing] = useOnProcessing();
  const [, setOnFinishedCodification] = useFinishedCodification();

  function openModal() {
    toggle();
  }

  function renderModal() {
    return (
      <ModalContainer>
        {explanation &&
          <img src={explanation}/>
        }
      </ModalContainer>
    );
  }

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
    if (files && files.length) {
      setFile(files[0]);
    }
  }

  async function inicialize() {
    if (codingDecoding === EncodingDecoding.NO_ONE) {
      toast.warn("Select the mode");
    } else if (codingDecoding!==EncodingDecoding.DECODING && !codificationMethod.codificationType) {
      toast.warn("Select an algorithm");
    } else if (!file.path) {
      toast.warn("Select a file");
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
        <FormRowHeader isDark={theme}>
          <span className="form-index">1</span>
          <Typografy.EMPHASYS text="Mode" />
        </FormRowHeader>
        <ButtonsRow>
          <Button.PRIMARY
            isDark={theme}
            isSelected={codingDecoding === EncodingDecoding.ENCODING}
            onClick={() => {
              handleCodificationMode(EncodingDecoding.ENCODING);
            }}>
            Encode
          </Button.PRIMARY>
          <Button.PRIMARY
          isDark={theme}
            isSelected={codingDecoding === EncodingDecoding.DECODING}
            onClick={() => {
              handleCodificationMode(EncodingDecoding.DECODING);
            }}>
            Decode
              </Button.PRIMARY>
        </ButtonsRow>
      </FormRow>
    )
  }

  function clickOnExplanation(codificationImage){
    setExplanation(codificationImage);
    openModal()
  }

  function renderCodificationMethod() {
    if (codingDecoding === EncodingDecoding.ENCODING) {
      return (
        <FormRow>
          <FormRowHeader isDark={theme}>
            <span className="form-index">2</span>
            <Typografy.EMPHASYS text="Select the encoding" />
          </FormRowHeader>
          <CodificationCards>
            {codifications.map((codification, index) => {
              return (
                <Card
                  isDark={theme}
                  key={index}
                  onClick={() => handleCodificationMethod(codification)}
                  isSelected={codificationMethod.codificationType === codification.codificationType}
                >
                  <div className="card-image">{codification.icon}</div>
                  <span className="card-name">{
                    codification.name.includes("Huffman") ? "Static Huffman" : (codification.name.includes("Unario") ? "Unary" : codification.name)
                  }</span>
                  { codification.explanationImage &&
                     <span className="explanation-icon" onClick={()=>{clickOnExplanation(codification.explanationImage)}}>
                     <Icon.Explanation size={24} color={theme?PRIMARY: 'black'}/>
                   </span>
                  }
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
          <FormRowHeader isDark={theme}>
            <span className="form-index">3</span>
            <Typografy.EMPHASYS text="Select the Goulomb divisor" />
          </FormRowHeader>
          <InputRow isDark={theme}>
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
        <FormRowHeader isDark={theme}>
          <span className="form-index">{getIndexOfFileRow()}</span>
          <Typografy.EMPHASYS text="Select the file" />
        </FormRowHeader>
        <InputRow isDark={theme} onClick={clickOnLabel}>
          <input
            ref={inputRef}
            type="file"
            onChange={handleInput}
          />
          <label>{file.name ? file.name : "No files selected"}</label>
          <Icon.Search color="#fff" size={20} />
        </InputRow>
      </FormRow>
    )
  }

  function renderInitButton() {
    return (
      <FormRow>
        <FormRowHeader isDark={theme}>
          <span className="form-index">{getIndexOfFileRow() + 1}</span>
          <Typografy.EMPHASYS text="Do the encoding" />
        </FormRowHeader>
        <ButtonsRow>
          <Button.CLICK onClick={inicialize}>
            Start
          </Button.CLICK>
        </ButtonsRow>
      </FormRow>
    )
  }

  function renderDevelopMode(){
    return (
      <DevCodification isDark={theme}>
        <Icon.Alert color={theme?'white':'black'} size={24}/>
        <p>This coding is currently under development</p>
      </DevCodification>
    )
  }

  return (
    <Container isDark={theme}>
      {renderEncodingDecoding()}
      {renderCodificationMethod()}
      {renderGoulombOptions()}
      {renderInputFile()}
      {codificationMethod.codificationType === CodificationMethod.HAMMING ? 
      renderDevelopMode()
      :
      renderInitButton()
      }

      <Modal
        title="Founders of this project"
        isShown={isShown}
        hide={toggle}
        content={renderModal()}
      />     
    </Container>
  );
};
