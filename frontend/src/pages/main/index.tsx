import React, { useEffect, useState } from 'react'
import { ExecutionWindow, SidePanel, Header, SyncLoader } from '../../components'
import { useTheme } from '../../context';
import {Container, MainProgram} from './styles'
import {sync} from '../../hooks/index'
import {SyncState} from '../../enums/SyncState'

export const Main = () => {

   const [onError, setOnError] = useState()
   const [theme] = useTheme();

   const [isSync, setIsSync] = useState(SyncState.UNSYNC);
   const LIMIT_REQUISITIONS = 15;
   const TIME_BETWEEN_REQUISITIONS = 2000;
   
   useEffect(()=>{
      if(isSync == SyncState.UNSYNC){
         getSyncConfirmation()
      }      
   },[isSync])

   async function getSyncConfirmation(){
      let i=0;

      const interval = setInterval(async ()=>{
         console.log('entrou')
         i++;
         if(i<LIMIT_REQUISITIONS){
            const response = await sync();
            if(response.data){
               setIsSync(SyncState.SYNC);
               clearInterval(interval);
            }
         }else{
            setIsSync(SyncState.FAILED)
            clearInterval(interval);
         }           
      }, TIME_BETWEEN_REQUISITIONS)        
   }

 return (
    <Container>
        <Header/>
        {isSync === SyncState.SYNC ?
         <MainProgram isDark={theme}>
            <SidePanel onError={onError}/>
            <ExecutionWindow onError={onError} setOnError={setOnError}/>
         </MainProgram>
        :
            <SyncLoader isSync={isSync} setIsSync={setIsSync}/>
         }
    </Container>
 )
};
