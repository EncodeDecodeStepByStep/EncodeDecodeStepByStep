import styled from 'styled-components';
import { PRIMARY } from '../../constants/colors';
export const Wrapper = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 700;
  width: inherit;
  outline: 0;
`;
export const Backdrop = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 500;
`;
export const StyledModal = styled.div`
  z-index: 100;
  background: white;
  position: relative;
  margin: auto;
  border-radius: 8px;
  padding: 10px;
`;
export const Header = styled.div`
  border-radius: 8px 8px 0 0;
  display: flex;
  justify-content: space-between;
  align-items:center;
  padding: 0.3rem;
`;

export const Title= styled.p`
  color: ${PRIMARY};
  font-weight:bold;

`

export const CloseButton = styled.button`
  border: none;
  background: none;
  cursor: pointer;
`;
export const Content = styled.div`
  padding: 10px;
  max-height: 30rem;
`;