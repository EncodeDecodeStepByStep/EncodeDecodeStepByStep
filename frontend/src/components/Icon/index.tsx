import React from 'react';
import {MdSearch} from 'react-icons/md'
import {FaUserAlt, FaArrowAltCircleRight} from 'react-icons/fa'
import {MdArrowBack, MdArrowForward, MdClose} from 'react-icons/md'
import {BsFillSkipEndFill, BsArrowDownShort} from 'react-icons/bs'
import {CgShapeTriangle} from 'react-icons/cg'
import { AiOutlineGoogle, AiFillLinkedin, AiFillGithub} from 'react-icons/ai'
import {GiBowlSpiral, GiRadioactive} from 'react-icons/gi'
import {RiStackshareLine, RiBubbleChartFill} from 'react-icons/ri'
import { XOR } from './style';
import {GoFileBinary} from 'react-icons/go'

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
            <GiBowlSpiral size={props.size} color={props.color}/>
        )
    }

    static Unario = (props:IconProps)=>{
        return (
            <GoFileBinary size={props.size} color={props.color}/>
        )
    }

    static Hamming = (props:IconProps)=>{
        return (
            <RiBubbleChartFill size={props.size} color={props.color}/>
        )
    }

    static Delta = (props:IconProps)=>{
        return (
            <CgShapeTriangle size={props.size} color={props.color}/>
        )
    }

    static Huffman = (props:IconProps)=>{
        return (
            <RiStackshareLine size={props.size} color={props.color}/>
        )
    }

    static Crc8 = (props:IconProps)=>{
        return (
            <XOR color={props.color}>XOR</XOR>
        )
    } 

    static Down = (props:IconProps)=>{
        return (
            <BsArrowDownShort size={props.size} color={props.color}/>
        )
    } 

    static Linkedin = (props:IconProps)=>{
        return (
            <AiFillLinkedin size={props.size} color={props.color}/>
        )
    }

    static Github = (props:IconProps)=>{
        return (
            <AiFillGithub size={props.size} color={props.color}/>
        )
    }
    
    
}