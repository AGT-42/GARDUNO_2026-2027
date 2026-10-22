function PokemonForm({ addPokemon }) {
function handleSubmit(e) {
    e.preventDefault();

const name = e.target.elements["name"].value;
const type = e.target.elements["type"].value;
const imageFile = e.target.elements["image"].files[0];

    if (name.trim() === "" || type.trim() === "" || !imageFile) {
    alert("Please fill in all fields.");
    return;
    }

    if (!imageFile.type.startsWith("image/")) {
    alert("Please select an image file.");
    return;
    }

    const image = URL.createObjectURL(imageFile);

    addPokemon({
    id: Date.now(),
    name: name,
    type: type,
    image: image
    });

e.target.reset();
}

return (
    <form
    onSubmit={handleSubmit}
    className="mx-auto mb-8 max-w-md rounded-xl bg-white p-6 shadow-md"
    >
    <h2 className="mb-4 text-xl font-bold">Add Pokémon</h2>

    <label className="mb-1 block">Pokémon Name</label>
    <input
        name="name"
        type="text"
        placeholder="Enter Pokémon name"
        className="mb-4 w-full rounded border p-2"
    />

    <label className="mb-1 block">Type</label>
    <input
        name="type"
        type="text"
        placeholder="e.g. Water, Fire, Grass"
        className="mb-4 w-full rounded border p-2"
    />

    <label className="mb-1 block">Upload Image</label>
    <input
        name="image"
        type="file"
        accept="image/*"
        className="mb-4 w-full rounded border p-2"
    />

    <button
        type="submit"
        className="w-full rounded bg-red-600 p-2 font-bold text-white hover:bg-green-500">
        Add Pokémon
    </button>
    </form>
    );
}

export default PokemonForm;
