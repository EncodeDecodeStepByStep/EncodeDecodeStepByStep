import React from "react";
import { useModal } from "../../hooks";
import { Icon, Modal } from "../index";
import { Container, ModalContent } from "./style";

import Logo from "../../assets/logo.png";
import LogoDark from "../../assets/logoDark.png";
import Programmer from "../../assets/programmer.mp4";
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
    return (
      <ModalContent>
        <video src={Programmer} autoPlay loop />
        <nav>
          <ul>
            <li>
              <div className="devname">Bruno Camboim</div>
              <div className="social-media">
                <a
                  rel="noopener noreferrer"
                  target="_blank"
                  href="https://www.linkedin.com/in/bruno-camboim3b6/"
                >
                  <Icon.Linkedin size={24} color="black" />
                </a>

                <a
                  target="_blank"
                  href="https://github.com/brunocamboim"
                  rel="noopener noreferrer"
                >
                  <Icon.Github size={24} color="black" />
                </a>
              </div>
            </li>
            <li>
              <div className="devname">Bruno Pagliarini Pozzebon</div>
              <div className="social-media">
                <a
                  rel="noopener noreferrer"
                  target="_blank"
                  href="https://www.linkedin.com/in/bruno-pozzebon44/"
                >
                  <Icon.Linkedin size={24} color="black" />
                </a>

                <a
                  rel="noopener noreferrer"
                  target="_blank"
                  href="https://github.com/brunopozzebon"
                >
                  <Icon.Github size={24} color="black" />
                </a>
              </div>
            </li>
            <li>
              <div className="devname">Gustavo Steinmetz</div>
              <div className="social-media">
                <a
                  rel="noopener noreferrer"
                  target="_blank"
                  href="https://www.linkedin.com/in/stzgustavo/"
                >
                  <Icon.Linkedin size={24} color="black" />
                </a>

                <a
                  rel="noopener noreferrer"
                  target="_blank"
                  href="https://github.com/GustavoSTZ"
                >
                  <Icon.Github size={24} color="black" />
                </a>
              </div>
            </li>
          </ul>
        </nav>
      </ModalContent>
    );
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
        title="Participaram deste projeto"
        isShown={isShown}
        hide={toggle}
        content={renderModal()}
      />
    </Container>
  );
};
