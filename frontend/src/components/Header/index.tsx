import React from 'react';
import { useModal } from '../../hooks';
import { Icon, Modal} from '../index';
import {Container, ModalContent} from './style';
import Programmer from '../../assets/programmer.mp4';
import Logo from '../../assets/logo.png';

export const Header = () => {
  
  const {isShown, toggle} = useModal();

  function openModal(){
    toggle()
  }

  function renderModal(){
      return (
      <ModalContent>        
          <video src={Programmer} autoPlay loop/>
          <nav>
            <ul>
              <li>
                <div className="devname">
                  Bruno Camboim
                </div>
                <div className="social-media">
                  <a target="_blank" href="https://www.linkedin.com/in/bruno-camboim3b6/">
                    <Icon.Linkedin size={24} color="black"/>
                  </a>

                  <a target="_blank" href="https://github.com/brunocamboim">
                    <Icon.Github size={24} color="black"/>
                  </a>
                </div>
               
                </li>
              <li><div className="devname">
                  Bruno Pagliarini Pozzebon
                </div>
                <div className="social-media">
                  <a target="_blank" href="https://www.linkedin.com/in/bruno-pozzebon44/">
                    <Icon.Linkedin size={24} color="black"/>
                  </a>

                  <a target="_blank" href="https://github.com/brunopozzebon">
                    <Icon.Github size={24} color="black"/>
                  </a>
                </div></li>
              <li>
              <div className="devname">
                  Gustavo Steinmetz
                </div>
                <div className="social-media">
                  <a target="_blank" href="https://www.linkedin.com/in/stzgustavo/">
                    <Icon.Linkedin size={24} color="black"/>
                  </a>

                  <a target="_blank" href="https://github.com/GustavoSTZ">
                    <Icon.Github size={24} color="black"/>
                  </a>
                </div>
              </li>
            </ul>
          </nav>
      </ModalContent>
    )
  }
  
  return (
    <Container>
        <img alt="logo" src={Logo}/>
        <div onClick={openModal} className="user-icon-container">
          <Icon.User color="#fff" size={22}/>
        </div>
        <Modal title="Participaram deste projeto" isShown={isShown} hide={toggle} content={renderModal()} />
    </Container>
  )
}
