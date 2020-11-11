import React from 'react';
import {Container} from './style'
import {useTheme} from '../../context'
import { Typografy } from '../Typografy';
import { Icon } from '../Icon';
import { SyncState } from '../../enums/SyncState';
import { Button } from '../Button';

export const SyncLoader = ({isSync, setIsSync}) =>{

    const [theme] = useTheme();

    return (
        <Container isDark={theme}>
            <div className="center-view">
                {isSync == SyncState.UNSYNC ?
                    <>
                        <Typografy.SUBTITLE className="text" text="Inicializando os serviços"/>
                        <p>Aguarde enquanto estamos inicializando os serviços da aplicação</p>
                        <span className="icon-container">
                            <Icon.Loader size={40} color={theme?'white':'black'}/>
                        </span>                        
                    </>
               :
                    <>
                        <Typografy.SUBTITLE className="text" text="Falha ao se conectar aos serviços"/>
                        <Button.PRIMARY isDark={theme} onClick={()=>{
                            setIsSync(SyncState.UNSYNC)
                        }}>Tentar novamente</Button.PRIMARY>
                    </>
                }
            </div>
        </Container>
    )
}