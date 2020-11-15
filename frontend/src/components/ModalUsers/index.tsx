import React from "react";
import { Container } from "./style";
import Programmer from "../../assets/programmer.mp4";
import { Icon } from "../Icon";
import { Typografy } from "../Typografy";

export const ModalUsers = () => {
  return (
    <Container>
      <p>
        Esse projeto foi desenvolvido durante a cadeira de Teoria da Informação,
        do curso de ciência da computação da Unisinos (2020), sob a orientação
        do professor Ernesto Lindstaedt.
      </p>

      <Typografy.EMPHASYS text="Integrantes" />
      <div className="teamwork-container">
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
        <video src={Programmer} autoPlay loop />
      </div>
    </Container>
  );
};
