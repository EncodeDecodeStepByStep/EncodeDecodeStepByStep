import createGlobalState from 'react-create-global-state';
import { Codeword } from '../models/codeword';
const [useCodewords, CodewordsProvider] = createGlobalState<Codeword>([])

export { useCodewords, CodewordsProvider }
