import createGlobalState from 'react-create-global-state';
import { EncodingDecoding } from '../enums/EncodingDecoding';

const [useCodingDecoding, CodingDecodingProvider] = createGlobalState(EncodingDecoding.NO_ONE)

export { useCodingDecoding, CodingDecodingProvider }
