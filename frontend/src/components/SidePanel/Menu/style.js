import styled from "styled-components";
import { GRAY, PRIMARY, BLUE_GRAY, BLUEISH, WHITE, LIGHT_GRAY, DARKEST } from "../../../constants/colors";

export const Container = styled.div`
  background-color: ${props => props.isDark ? BLUE_GRAY: WHITE};
  border-radius: 10px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  max-width: 30%;
  overflow-y: auto;
`;

export const FormRow = styled.div`
 
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
  animation: 0.5s surgir forwards ease-in-out;
`;

export const FormRowHeader = styled.header`
  color:${props => props.isDark ? 'white': 'black'};
  display: flex;
  justify-content: space-space-between;
  align-items: center;
  margin-bottom:15px;

  .form-index {
    height: 20px;
    width: 20px;
    color: black;
    background-color: ${PRIMARY};
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    padding: 4px;
    margin-right: 15px;
    font-size: 12px;
  }
`;

export const InputRow = styled.div`
  background-color:${props => props.isDark ? GRAY: LIGHT_GRAY};
  
  padding: 10px 15px;
  border-radius: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor:pointer;

  input{
    width:100%;
    background-color:transparent;
    color:white;
    border:none;
  }

  input[type='file'] {
    display: none;
  }

  label {
    cursor:pointer;
    color: white;
    font-size: 12px;
  }
`;

export const ButtonsRow = styled.div`
  display: flex;
  margin-top: 5px;
  padding-left: 40px;
  & > button {
    margin-right: 15px;
  }
`;

export const CodificationCards = styled.section`
  grid-template-columns: 1fr 1fr 1fr 1fr;
  display: grid;
  grid-gap: 10px;
  margin-top: 10px;
`;

export const Card = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;

  .card-image {
    border-radius: 10px;
    background-color: ${(props) =>
      props.isSelected ? PRIMARY : props.isDark?BLUEISH:LIGHT_GRAY};
    width: 60px;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;

    svg{
      color: ${props=>!props.isDark && 'white !important'}
    }

    &:hover {
      opacity: 0.8;
    }
  }

  .card-name {
    color: ${BLUEISH};
    font-size: 0.8rem;
    text-align: center;
    margin: 10px 0 5px 0;
  }
`;

export const DevCodification = styled.div`
    background-color: ${(props) =>props.isDark?DARKEST:LIGHT_GRAY};
    color:${(props) =>props.isDark?'white':'black'};
    border-radius:10px;
    padding:10px;
    display:flex;
    flex-direction:row;
    align-items:center;

    p{
      margin-left:10px;
      font-size:0.8rem;
    }
`
