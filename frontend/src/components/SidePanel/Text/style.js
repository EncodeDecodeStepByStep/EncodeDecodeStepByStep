import styled from 'styled-components'
import { PRIMARY } from '../../../constants/colors'

export const Container = styled.div`
    background-color: rgb(44, 49, 68);
    border-radius: 10px;
    padding: 16px;
    display: flex;
    flex-direction: column;
    width: 30%;
    overflow-y: auto;

    .text-title{
        color:${PRIMARY};
        text-align:center;
    }

    p{
        margin-top: 10px;
        text-align: center;
        color:white;
    }
`