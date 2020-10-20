import React, { useState } from 'react'
import { ExecutionWindow, SidePanel, Header } from '../../components'
import {Container, MainProgram} from './styles'

export const Main = () => {

   const [onError, setOnError] = useState()
 return (
    <Container>
        <Header/>
        <MainProgram>
           <SidePanel onError={onError}/>
           <ExecutionWindow onError={onError} setOnError={setOnError}/>
        </MainProgram>
    </Container>
 )
};
