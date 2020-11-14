import React from "react";
import { useModal } from "../../hooks";
import { Icon, Modal } from "../index";
import { Container } from "./style";

import Logo from "../../assets/logo.png";
import LogoDark from "../../assets/logoDark.png";
import Toggle from "react-toggle";
import {
  useCodewords,
  useCodificationMethod,
  useCodingDecoding,
  useFinishedCodification,
  useIndex,
  useOnProcessing,
  useTheme,
} from "../../context";
import { EncodingDecoding } from "../../enums/EncodingDecoding";
import { CodificationMethod } from "../../enums/CodificationMethod";
import { ModalUsers } from "../ModalUsers";

export const Header = () => {
  const { isShown, toggle } = useModal();
  const [theme, setTheme] = useTheme();

  const [, setOnProcessing] = useOnProcessing();
  const [, setOnFinishedCodification] = useFinishedCodification();
  const [, setCodificationMethod] = useCodificationMethod();
  const [, setCodewords] = useCodewords();
  const [, setCodingDecoding] = useCodingDecoding();
  const [, setIndex] = useIndex();

  function openModal() {
    toggle();
  }

  function renderModal() {
    return <ModalUsers/>;
  }

  function clickOnLogo() {
    setOnProcessing(false);
    setOnFinishedCodification(false);
    setCodingDecoding(EncodingDecoding.NO_ONE);
    setCodificationMethod(CodificationMethod.NO_ONE);
    setCodewords([]);
    setIndex(1);
  }

  return (
    <Container isDark={theme}>
      <img onClick={()=>clickOnLogo()} alt="logo" src={theme ? Logo : LogoDark} />
      <div className="right-content">
        <div className="toggle-container">
          <Toggle
            defaultChecked={true}
            icons={false}
            onChange={() => {
              setTheme(!theme);
            }}
          />
          <span className="toggle-name">
            {theme ? "Dark Mode" : "Light Mode"}
          </span>
        </div>

        <div onClick={openModal} className="user-icon-container">
          <Icon.User color={theme ? "#fff" : "#000"} size={22} />
        </div>
      </div>

      <Modal
        title="Sobre o projeto"
        isShown={isShown}
        hide={toggle}
        content={renderModal()}
      />
    </Container>
  );
};
