import React from 'react';
import {MdSearch} from 'react-icons/md'
import {FaUserAlt, FaArrowAltCircleRight} from 'react-icons/fa'
import {MdArrowBack, MdArrowForward, MdClose} from 'react-icons/md'
import {BsFillSkipEndFill} from 'react-icons/bs'
import {CgShapeTriangle} from 'react-icons/cg'
import {AiOutlineNodeCollapse, AiOutlineGoogle, AiOutlineFieldBinary} from 'react-icons/ai'
import {TiFlowMerge} from 'react-icons/ti'
import {GiRadioactive} from 'react-icons/gi'

type IconProps = {
    size: number, 
    color: string
}

export class Icon{
    static Search= (props : IconProps)=>{
        return (
            <MdSearch size={props.size} color={props.color}/>
        )
    }

    static User = (props : IconProps)=>{
        return (
            <FaUserAlt size={props.size} color={props.color}/>
        )
    }

    static TransformTo = (props : IconProps)=>{
        return (
            <FaArrowAltCircleRight size={props.size} color={props.color}/>
        )
    }

    static Next = (props : IconProps)=>{
        return (
            <MdArrowForward size={props.size} color={props.color}/>
        )
    }

    static Back = (props : IconProps)=>{
        return (
            <MdArrowBack size={props.size} color={props.color}/>
        )
    }

    static Finish = (props : IconProps)=>{
        return (
            <BsFillSkipEndFill size={props.size} color={props.color}/>
        )
    }

    static Close = (props:IconProps)=>{
        return (
            <MdClose size={props.size} color={props.color}/>
        )
    }

    static Goulomb = (props:IconProps)=>{
        return (
            <AiOutlineGoogle size={props.size} color={props.color}/>
        )
    }

    static EliasGamma = (props:IconProps)=>{
        return (
            <GiRadioactive size={props.size} color={props.color}/>
        )
    }

    static Fibonatti = (props:IconProps)=>{
        return (
            <AiOutlineNodeCollapse size={props.size} color={props.color}/>
        )
    }

    static Unario = (props:IconProps)=>{
        return (
            <AiOutlineFieldBinary size={props.size} color={props.color}/>
        )
    }

    static Hamming = (props:IconProps)=>{
        return (
            <TiFlowMerge size={props.size} color={props.color}/>
        )
    }

    static Delta = (props:IconProps)=>{
        return (
            <CgShapeTriangle size={props.size} color={props.color}/>
        )
    }

    
}