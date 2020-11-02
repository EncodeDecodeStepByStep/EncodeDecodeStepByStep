import React from 'react';
import {MdSearch} from 'react-icons/md'
import {FaUserAlt, FaArrowAltCircleRight} from 'react-icons/fa'
import {MdArrowBack, MdArrowForward, MdClose} from 'react-icons/md'
import {BsFillSkipEndFill, BsArrowDownShort} from 'react-icons/bs'
import {CgShapeTriangle} from 'react-icons/cg'
import { AiOutlineGoogle, AiFillLinkedin, AiFillGithub, AiFillMinusCircle, AiFillPlusCircle} from 'react-icons/ai'
import {GiBowlSpiral, GiRadioactive} from 'react-icons/gi'
import {RiStackshareLine, RiBubbleChartFill, RiExchangeFill} from 'react-icons/ri'
import {GoFileBinary} from 'react-icons/go'

type IconProps = {
    size: number, 
    color: string
}

export class Icon{
    static Search= (props : IconProps)=>{
        return (
            <MdSearch className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static User = (props : IconProps)=>{
        return (
            <FaUserAlt className="custom-icon" size={props.size} color={props.color}/>
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
            <MdClose className="custom-icon"  size={props.size} color={props.color}/>
        )
    }

    static Goulomb = (props:IconProps)=>{
        return (
            <AiOutlineGoogle className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static EliasGamma = (props:IconProps)=>{
        return (
            <GiRadioactive className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Fibonatti = (props:IconProps)=>{
        return (
            <GiBowlSpiral className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Unario = (props:IconProps)=>{
        return (
            <GoFileBinary className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Hamming = (props:IconProps)=>{
        return (
            <RiBubbleChartFill className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Delta = (props:IconProps)=>{
        return (
            <CgShapeTriangle className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Huffman = (props:IconProps)=>{
        return (
            <RiStackshareLine className="custom-icon" size={props.size} color={props.color}/>
        )
    }

    static Down = (props:IconProps)=>{
        return (
            <BsArrowDownShort size={props.size} color={props.color}/>
        )
    } 

    static Linkedin = (props:IconProps)=>{
        return (
            <AiFillLinkedin className="network-icon"  size={props.size} color={props.color}/>
        )
    }

    static Github = (props:IconProps)=>{
        return (
            <AiFillGithub className="network-icon"  size={props.size} color={props.color}/>
        )
    }

    static Plus = (props:IconProps)=>{
        return (
            <AiFillPlusCircle  className="custom-icon"  size={props.size} color={props.color}/>
        )
    }

    static Minus = (props:IconProps)=>{
        return (
            <AiFillMinusCircle className="custom-icon"  size={props.size} color={props.color}/>
        )
    }

    static Changed = (props:IconProps)=>{
        return (
            <RiExchangeFill className="custom-icon"  size={props.size} color={props.color}/>
        )
    }
    
    
}