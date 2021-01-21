package com.example.notes.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.data.model.NotesData
import com.example.notes.data.viewmodel.NotesViewModel
import com.example.notes.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private val mNotesViewModel: NotesViewModel by viewModels()
    private val mSharedViewMode: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)

        view.spinnerPriorities.onItemSelectedListener = mSharedViewMode.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAdd) {
            insertDataToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = etTitle.text.toString()
        val mPriority = spinnerPriorities.selectedItem.toString()
        val mDescription = etDescription.text.toString()

        val validation = mSharedViewMode.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            val  newData = NotesData(
                0,
                mTitle,
                mSharedViewMode.parsePriority(mPriority),
                mDescription
            )
            mNotesViewModel.insertData(newData)
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }
}