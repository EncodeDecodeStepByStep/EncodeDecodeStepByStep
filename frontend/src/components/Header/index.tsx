import React from 'react';
import { useModal } from '../../hooks';
import { Icon, Modal} from '../index';
import { Typografy } from '../Typografy';
import {Container, ModalContent} from './style'
import Programmer from '../../assets/programmer.mp4'
import axios from 'axios'

export const Header = () => {
  
  const {isShown, toggle} = useModal();

  function openModal(){
    toggle()
  }

  function renderModal(){
      axios.get('http://localhost:8080')
          .then(function(response){
              console.log(response.data); // ex.: { user: 'Your User'}
              console.log(response.status); // ex.: 200
          });

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
