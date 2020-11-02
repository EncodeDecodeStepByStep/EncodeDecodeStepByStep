import React, { useState } from 'react'
import { ExecutionWindow, SidePanel, Header } from '../../components'
import { useTheme } from '../../context';
import {Container, MainProgram} from './styles'

export const Main = () => {

   const [onError, setOnError] = useState()
   const [theme] = useTheme();
 return (
    <Container>
        <Header/>
        <MainProgram isDark={theme}>
           <SidePanel onError={onError}/>
           <ExecutionWindow onError={onError} setOnError={setOnError}/>
        </MainProgram>
    </Container>
 )
};
