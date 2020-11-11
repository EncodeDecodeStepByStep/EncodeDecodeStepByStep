import styled from 'styled-components';
import { PRIMARY } from '../../../constants/colors';

export const Container = styled.div`

  display:flex;
  flex-direction:column;
  justify-content:center;

  img {
    margin-bottom: 16px;
    object-fit: contain;
  }

  h2 {
    margin-bottom: 20px;
    text-align: center;
    font-size: 22px;
    color: ${PRIMARY};
  }

  p {
    color: ${(props) => (props.isDark ? "white" : "black")};
  }

  button{
    margin-top:10px;
  }
`;