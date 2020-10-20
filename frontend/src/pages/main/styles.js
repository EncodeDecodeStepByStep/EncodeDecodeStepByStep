import styled from 'styled-components' 
import { GRAY } from '../../constants/colors'

export const Container = styled.div`
    height: 100vh;
    width: 100vw;
    overflow:hidden;
`

export const MainProgram = styled.main`
    width:100vw;
    height:calc(100vh - 80px);
    max-height:calc(100vh - 80px);
    display:flex;
    background-color:${GRAY};
    padding:20px;
`

