import { Icon } from "../components";
import { CodificationMethod } from "../enums/CodificationMethod";
import React from 'react';
import { Codification } from '../models/codification'

import GoulombExplanation from '../assets/codificationsExplanations/Goulomb.png';
import DeltaExplanation from '../assets/codificationsExplanations/Delta.png';
import UnarioExplanation from '../assets/codificationsExplanations/Unario.png';
import HuffmanExplanation from '../assets/codificationsExplanations/Huffman.png';
import EliasExplanation from '../assets/codificationsExplanations/EliasGamma.png';
import FibonacciExplanation from '../assets/codificationsExplanations/Fibonacci.png';


export default [
    new Codification(
        "Goulomb",
        "goulomb",
        CodificationMethod.GOULOMB,
        <Icon.Goulomb size={40} color="#333" />,
        GoulombExplanation
    ),
    new Codification(
        "Elias Gamma",
        "elias-gamma",
        CodificationMethod.ELIAS_GAMMA,
        <Icon.EliasGamma size={40} color="#333" />,
        EliasExplanation
    ), new Codification(
        "Delta",
        "delta",
        CodificationMethod.DELTA,
        <Icon.Delta size={40} color="#333" />,
        DeltaExplanation
    ), new Codification(
        "Fibonacci",
        "fibonacci",
        CodificationMethod.FIBONACCI,
        <Icon.Fibonatti size={40} color="#333" />,
        FibonacciExplanation
    ), new Codification(
        "Unary",
        "unary",
        CodificationMethod.UNARIO,
        <Icon.Unario size={40} color="#333" />,
        UnarioExplanation
    ), new Codification(
        "Hamming",
        "hamming",
        CodificationMethod.HAMMING,
        <Icon.Hamming size={40} color="#333" />,
         null
    ), new Codification(
        "Static Huffman",
        "huffman",
        CodificationMethod.HUFFMAN,
        <Icon.Huffman size={40} color="#333"/>,
        HuffmanExplanation
    )
]