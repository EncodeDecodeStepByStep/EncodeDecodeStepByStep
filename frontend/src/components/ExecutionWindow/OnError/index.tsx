import React from 'react';
import { Button } from '../../Button';
import { Typografy } from '../../Typografy';
import {Container} from './styles';
import ErrorGif from "../../../assets/error.gif";
import { useTheme } from '../../../context';

export const OnError = ({finish})=>{

    const [theme] = useTheme();
    
    return (
        <Container isDark={theme}>
          <img alt="error" src={ErrorGif} />
          <Typografy.SUBTITLE text="Ops, algo deu errado" />
          <p>Não foi possível se conectar ao servidor</p>
          <Button.PRIMARY onClick={()=>finish()} isDark={theme}>Voltar</Button.PRIMARY>
        </Container>
    )
}