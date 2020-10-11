import styled from "styled-components";
import { PRIMARY } from "../../constants/colors";

export const Container = styled.div`
  background-color: rgb(44, 49, 68);
  border-radius: 10px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  max-width: 30%;
`;

export const FormRow = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
`;

export const FormRowHeader = styled.header`
  display: flex;
  justify-content: space-space-between;
  color: white;
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
  background-color: rgb(39, 44, 60);
  padding: 10px 15px;
  border-radius: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor:pointer;

  input {
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
      props.isSelected ? PRIMARY : "rgb(93,103,134)"};
    width: 60px;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;

    &:hover {
      opacity: 0.8;
    }
  }

  .card-name {
    color: rgb(93, 103, 134);
    font-size: 0.8rem;
    text-align: center;
    margin: 10px 0 5px 0;
  }
`;

export const ClosedContainer = styled.div``;
