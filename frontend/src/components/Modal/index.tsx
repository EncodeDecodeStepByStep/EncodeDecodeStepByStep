import React, { FunctionComponent } from 'react';
import ReactDOM from 'react-dom';
import { Icon } from '../Icon';
import {
  Wrapper,
  Header,
  StyledModal,
  CloseButton,
  Content,
  Backdrop,
  Title
} from './styles';

export interface ModalProps {
  isShown: boolean;
  hide: () => void;
  content: JSX.Element;
  title?: string;
}
export const Modal: FunctionComponent<ModalProps> = ({
  isShown,
  hide,
  content,
  title
}) => {
  const modal = (
    <React.Fragment>
      <Backdrop />
      <Wrapper>
        <StyledModal>
          <Header>
            <Title>{title}</Title>
            <CloseButton onClick={hide}>
              <Icon.Close size={24} color="#000"/>
            </CloseButton>
          </Header>
          <Content>{content}</Content>
        </StyledModal>
      </Wrapper>
    </React.Fragment>
  );
  return isShown ? ReactDOM.createPortal(modal, document.body) : null;
};