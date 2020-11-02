import styled from 'styled-components';
import { GRAY, LIGHT_GRAY, PRIMARY } from '../../constants/colors';


const Button = styled.button`
    border-radius: 10px;
    padding:8px 14px;
    border:none;
    cursor:${props => props.disabled? 'not-allowed':'pointer' };
    opacity:${props => props.disabled? 0.3: 1 };
    display: flex;
    align-items: center;
    outline:none;
    justify-content:center;
    transition:0.5s;
    color:white;

    svg{
        margin-right:5px;
        padding-left:0px;
    }

    &:hover{
        transform:scale(1.05);
        transition:0.5s;
    }
`

export const Primary = styled(Button)`
    background-color:${props => props.isSelected? PRIMARY: props.isDark?GRAY:LIGHT_GRAY };
    
`

export const Click = styled(Button)` 
    background-color:${PRIMARY};
    width:100%;
`