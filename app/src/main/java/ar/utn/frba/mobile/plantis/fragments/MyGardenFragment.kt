package ar.utn.frba.mobile.plantis.fragments

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentMyGardenBinding
import java.time.DayOfWeek
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O) // TODO: ver como sacar esto
class MyGardenFragment : Fragment() {
    lateinit var binding: FragmentMyGardenBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyGardenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantList = mutableListOf(
            PlantDetail("Pothos", "Epipremnum aureum",
                """
                    Epipremnum aureum is a species in the arum family Araceae, native to Mo'orea in the Society Islands of French Polynesia. The species is a popular houseplant in temperate regions but has also become naturalised in tropical and sub-tropical forests worldwide, including northern South Africa, Australia, Southeast Asia, South Asia, the Pacific Islands and the West Indies, where it has caused severe ecological damage in some cases.
                    The plant has a number of common names including golden pothos, Ceylon creeper, hunter's robe, ivy arum, house plant, money plant, silver vine, Solomon Islands ivy, marble queen, and taro vine. It is also called devil's vine or devil's ivy because it is almost impossible to kill and it stays green even when kept in the dark. It is sometimes mistakenly labeled as a Philodendron in plant stores. It is commonly known as a money plant in many parts of the Indian subcontinent. It rarely flowers without artificial hormone supplements; the last known spontaneous flowering in cultivation was reported in 1964.
                    The plant has gained the Royal Horticultural Society's Award of Garden Merit.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Epipremnum_aureum_31082012.jpg/320px-Epipremnum_aureum_31082012.jpg",
                "https://en.wikipedia.org/wiki/Epipremnum_aureum",
                listOf(
                    Reminder("Water", LocalTime.of(17, 0), listOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY)),
                    Reminder("Renew Ground", LocalTime.of(11, 0), listOf(DayOfWeek.SUNDAY)),
                    Reminder("Fertilize", LocalTime.of(8, 30), listOf(DayOfWeek.TUESDAY)),
                    Reminder("Check", LocalTime.of(7, 0), listOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
                )
            ),
            PlantDetail("Succulent", "Astroloba tenax",
                "Astroloba tenax is a succulent plant of the genus Astroloba, indigenous to the Western Cape Province, South Africa.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Astroloba_tenax_6.jpg/304px-Astroloba_tenax_6.jpg",
                "https://en.wikipedia.org/wiki/Astroloba_tenax"),
            PlantDetail("Cactus", "Cactoideae",
                """
                    The Cactoideae are the largest subfamily of the cactus family, Cactaceae. Around 80% of cactus species belong to this subfamily. As of August 2018, the internal classification of the family Cactaceae remained uncertain and subject to change. A classification incorporating many of the insights from the molecular studies was produced by Nyffeler and Eggli in 2010. Various revisions have been published since, e.g. to the tribe Hylocereeae and the tribe Echinocereeae. Classifications remained uncertain as of March 2019.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Warzenkaktus_-_Mammillaria_elongata.JPG/320px-Warzenkaktus_-_Mammillaria_elongata.JPG",
                "https://en.wikipedia.org/wiki/Cactoideae"),
            PlantDetail("Basil", "Ocimum basilicum",
                """
                    Basil, also called great basil, is a culinary herb of the family Lamiaceae (mints). It is a tender plant, and is used in cuisines worldwide. In Western cuisine, the generic term "basil" refers to the variety also known as sweet basil or Genovese basil. Basil is native to tropical regions from Central Africa to Southeast Asia. In temperate climates basil is treated as an annual plant, however, basil can be grown as a short-lived perennial or biennial in warmer horticultural zones with tropical or Mediterranean climates.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Basil-Basilico-Ocimum_basilicum-albahaca.jpg/320px-Basil-Basilico-Ocimum_basilicum-albahaca.jpg",
                "https://en.wikipedia.org/wiki/Basil"),
            PlantDetail("Parsley", "Petroselinum crispum",
                """
                    Parsley, or garden parsley (Petroselinum crispum) is a species of flowering plant in the family Apiaceae that is native to the central and eastern Mediterranean region (Sardinia, Lebanon, Israel, Cyprus, Turkey, southern Italy, Greece, Portugal, Spain, Malta, Morocco, Algeria, and Tunisia), but has been naturalized elsewhere in Europe, and is widely cultivated as a herb, and a vegetable.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Petroselinum.jpg/171px-Petroselinum.jpg",
                "https://en.wikipedia.org/wiki/Parsley"),
            PlantDetail("Rosemary", "Salvia rosmarinus",
                """
                    Salvia rosmarinus, commonly known as rosemary, is a shrub with fragrant, evergreen, needle-like leaves and white, pink, purple, or blue flowers, native to the Mediterranean region. Until 2017, it was known by the scientific name Rosmarinus officinalis, now a synonym.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Rosemary_Plant.jpg/180px-Rosemary_Plant.jpg",
                "https://en.wikipedia.org/wiki/Rosemary"),
            PlantDetail("Mint", "Mentha balsamea",
                """
                    Peppermint (Mentha piperita, also known as Mentha balsamea Wild) is a hybrid mint, a cross between watermint and spearmint. Indigenous to Europe and the Middle East,[3] the plant is now widely spread and cultivated in many regions of the world. It is occasionally found in the wild with its parent species.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Pfefferminze_natur_peppermint.jpg/320px-Pfefferminze_natur_peppermint.jpg",
                "https://en.wikipedia.org/wiki/Peppermint"),
            PlantDetail("Oregano", "Origanum vulgare",
                """
                    Oregano (US: /ɔːˈrɛɡənoʊ, ə-/, UK: /ˌɒrɪˈɡɑːnoʊ/; Origanum vulgare) is a species of flowering plant in the mint family Lamiaceae. It was native to the Mediterranean region, but widely naturalised elsewhere in the temperate Northern Hemisphere.
                    Oregano is a woody perennial plant, growing 20–80 cm (8–31 in) tall, with opposite leaves 1–4 cm (1⁄2–1+1⁄2 in) long. The flowers are purple, 3–4 mm (1⁄8–3⁄16 in) long, produced in erect spikes in summer. It is sometimes called wild marjoram, and its close relative, O. majorana, is known as sweet marjoram. Both are widely used as culinary herbs, especially in Turkish, Greek, Spanish, Italian, Mexican, and French cuisine. Oregano is also an ornamental plant, with numerous cultivars bred for varying leaf colour, flower colour and habit.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Majorana_syriaca_-_za%27atar.jpg/320px-Majorana_syriaca_-_za%27atar.jpg",
                "https://en.wikipedia.org/wiki/Oregano"),
            PlantDetail("Thyme", "Thymus vulgaris",
                """
                    Thymus vulgaris (common thyme, German thyme, garden thyme or just thyme) is a species of flowering plant in the mint family Lamiaceae, native to southern Europe from the western Mediterranean to southern Italy. Growing to 15–30 cm (6–12 in) tall by 40 cm (16 in) wide, it is a bushy, woody-based evergreen subshrub with small, highly aromatic, grey-green leaves and clusters of purple or pink flowers in early summer.
                """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/4/4f/Thymus_vulgaris1.JPG",
                "https://en.wikipedia.org/wiki/Thymus_vulgaris")
        )

        val storagePlants = PlantisStorage.getPlants(requireActivity()).plantis
        plantList.addAll(storagePlants)

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = GardenAdapter(view, plantList)

        recyclerView = binding.myRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.addButton.setOnClickListener { goToSearch() }
    }

    private fun goToSearch() {
        val action = R.id.action_myGardenFragment_to_searchFragment
        val bundle = bundleOf("wantsToAddPlant" to true)
        findNavController().navigate(action, bundle)
    }
}