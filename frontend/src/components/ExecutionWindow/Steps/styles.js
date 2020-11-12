import styled,{css} from 'styled-components';
import { PRIMARY } from '../../../constants/colors';


export const Container = styled.section`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

export const Buttons = styled.section`
  display: flex;
  align-self: center;
  justify-content: center;
  position: fixed;
  bottom: 30px;

  button {
    margin: 10px;
  }
`;

export const StepsCanva = styled.div`
  width: 100%;
  height: 100%;

  header {
    display: flex;
    justify-content: space-between;
    margin: 15px 10px;

    .counter {
      color: ${PRIMARY};
    }

    .codification-title {
      color: ${(props) => (props.isDark ? "white" : "black")};
    }
  }
`;

export const ScroolingList = styled.div`
  ${(props) =>
    props.scrool &&
    css`
      overflow-y: auto;
      max-height: calc(100% - 100px);
    `}
`;
