import styled from 'styled-components';
import { PRIMARY } from '../../../constants/colors';

export const Container = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  svg {
    width: fit-content;
  }

  h2 {
    color: ${PRIMARY};
    margin-bottom: 20px;
  }

  & > div {
    display: flex;
    justify-content: center;
  }

  button {
    margin-top: 15px;
  }

  .percentage-value {
    margin-top: 10px;
    display: flex;
    align-items: center;

    span {
      color: ${PRIMARY};
      font-weight: bold;
      margin-right: 10px;
    }
  }

  p {
    color: ${(props) => (props.isDark ? "white" : "black")};
    font-weight: bold;
    margin: 15px 0;
  }
`;