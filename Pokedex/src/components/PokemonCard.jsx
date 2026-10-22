
function PokemonCard({ pokemon }) {
return (
    <div className="rounded-xl bg-white p-5 text-center shadow-md">
    <img
        src={pokemon.image}
        alt={pokemon.name}
        className="mx-auto mb-3 h-32 w-32 object-contain"
    />

    <h2 className="text-xl font-bold">{pokemon.name}</h2>

    <p className="mt-2 inline-block rounded-full bg-green-100 px-4 py-1 text-green-800">
        {pokemon.type}
    </p>
    </div>
);
}

export default PokemonCard;
