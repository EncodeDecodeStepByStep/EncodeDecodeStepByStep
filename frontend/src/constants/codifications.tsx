import { Icon } from "../components";
import { CodificationMethod } from "../enums/CodificationMethod";
import React from 'react';

export default [
    {
        name:"Goulomb",
        index: CodificationMethod.GOULOMB,
        image: <Icon.Goulomb size={40} color="#333"/>
    },{
        name:"Elias Gamma",
        index: CodificationMethod.ELIAS_GAMMA,
        image: <Icon.EliasGamma size={40} color="#333"/>
    },{
        name:"Delta",
        index: CodificationMethod.DELTA,
        image: <Icon.Delta size={40} color="#333"/>
    },{
        name:"Fibonacci",
        index: CodificationMethod.FIBONACCI,
        image: <Icon.Fibonatti size={40} color="#333"/>
    },{
        name:"Unario",
        index: CodificationMethod.UNARIO,
        image: <Icon.Unario size={40} color="#333"/>
    }
]