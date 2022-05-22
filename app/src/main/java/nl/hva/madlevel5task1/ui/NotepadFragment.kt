package nl.hva.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.madlevel5task1.R
import com.example.madlevel5task1.databinding.FragmentNotepadBinding
import nl.hva.madlevel5task1.vm.NoteViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotepadFragment : Fragment() {

    private var _binding: FragmentNotepadBinding? = null
    private val viewModel: NoteViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotepadBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, { note ->
            note?.let {
                binding.tvNoteTitle.text = it.title
                binding.tvLastUpdated.text = getString(R.string.last_updated, it.lastUpdated.toString())
                binding.tvNoteText.text = it.text
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}