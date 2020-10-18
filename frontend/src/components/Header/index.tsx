import React from 'react';
import { useModal } from '../../hooks';
import { Icon, Modal} from '../index';
import {Container, ModalContent} from './style'
import Programmer from '../../assets/programmer.mp4'

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
              <li>Bruno Camboim</li>
              <li>Bruno Pagliarini Pozzebon</li>
              <li>Gustavo Steinmetz</li>
            </ul>
          </nav>
      </ModalContent>
    )
  }
  
  return (
    <Container>
        <div>Logo</div>

        <div onClick={openModal} className="user-icon-container">
          <Icon.User color="#fff" size={22}/>
        </div>
        <Modal title="Participaram deste projeto" isShown={isShown} hide={toggle} content={renderModal()} />
    </Container>
  )
}
