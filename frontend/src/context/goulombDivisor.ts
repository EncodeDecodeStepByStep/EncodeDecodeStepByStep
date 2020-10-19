import createGlobalState from 'react-create-global-state';

const [useGoulombDivisor, GoulombDivisorProvider] = createGlobalState(4)

export { useGoulombDivisor, GoulombDivisorProvider }
